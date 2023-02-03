/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.booking.consts.BookingStatus;
import com.sapient.booking.consts.Exceptions;
import com.sapient.booking.entities.Booking;
import com.sapient.booking.entities.Customer;
import com.sapient.booking.entities.Offer;
import com.sapient.booking.entities.Seat;
import com.sapient.booking.entities.Show;
import com.sapient.booking.exceptions.CommonAPIException;
import com.sapient.booking.mappers.TheatreMapper;
import com.sapient.booking.models.BookingDetailDTO;
import com.sapient.booking.models.BookingRequestDTO;
import com.sapient.booking.models.CustomerDTO;
import com.sapient.booking.models.TicketDTO;
import com.sapient.booking.repositories.BookingRepository;
import com.sapient.booking.repositories.CustomerRepository;
import com.sapient.booking.repositories.OfferRepository;
import com.sapient.booking.repositories.SeatRepository;
import com.sapient.booking.repositories.ShowRepository;
import com.sapient.booking.services.BookingService;
import com.sapient.booking.utils.AppWebUtils;

/**
 * The Class BookingServiceImpl.
 * @author Sandeep Kumar
 */
@Service
public class BookingServiceImpl implements BookingService {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	/** The booking repository. */
	@Autowired
	private BookingRepository bookingRepository;
	
	/** The offer repository. */
	@Autowired
	private OfferRepository offerRepository;
	
	/** The show repository. */
	@Autowired
	private ShowRepository showRepository;
	
	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;
	
	/** The seat repository. */
	@Autowired
	private SeatRepository seatRepository;

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.BookingService#booking(java.util.List)
	 */
	@Override
	@Transactional
	public List<BookingDetailDTO> booking(List<BookingRequestDTO> bookingRequests) throws CommonAPIException {
		logger.info("Booking entry");
		if(null == bookingRequests || bookingRequests.isEmpty()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		List<BookingDetailDTO> retVal = new ArrayList<>();
		for(BookingRequestDTO request:bookingRequests) {
			BookingDetailDTO bookingDetailDto = new BookingDetailDTO();
			retVal.add(bookingDetailDto);
			if(null == request || null == request.getCustomer() || null == request.getShow() || null == request.getSeats() || request.getSeats().isEmpty()) {
				bookingDetailDto.setStatus(BookingStatus.FAILED.toString());
				bookingDetailDto.setMessage("Invalid input e.g. show, customer or ticketcount");
				continue;
			}
			if(!customerRepository.existsByCustomerIdAndActive(request.getCustomer())) {
				bookingDetailDto.setStatus(BookingStatus.FAILED.toString());
				bookingDetailDto.setMessage("Customer not available or not active");
				continue;
			}
			if(!showRepository.isShowByid(request.getShow())) {
				bookingDetailDto.setStatus(BookingStatus.FAILED.toString());
				bookingDetailDto.setMessage("Show not available");
				continue;
			}
			Show show = showRepository.getOne(request.getShow());
			Set<Seat> bookingSeats = show.getSeats().stream().filter(seat -> request.getSeats().contains(seat.getCode()) && !seat.getBooked()).collect(Collectors.toSet());
			if(bookingSeats.size() != request.getSeats().size()) {
				bookingDetailDto.setStatus(BookingStatus.FAILED.toString());
				bookingDetailDto.setMessage("Requested ticket not available:");
				continue;
			}
			Customer customer = customerRepository.getOne(request.getCustomer());
			BigDecimal projectedCost = new BigDecimal(show.getPrice()).multiply(new BigDecimal(request.getSeats().size()));
			BigDecimal projectTax = (new BigDecimal(show.getTax()).multiply(projectedCost)).divide(new BigDecimal(100));
			BigDecimal discount = calculateDiscunt(show, request, projectedCost);
			BigDecimal finalCost = projectedCost.subtract(discount).add(projectTax);
			Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setBasePrice(show.getPrice());
			booking.setTax(projectTax.floatValue());
			booking.setDiscount(discount.floatValue());
			booking.setFinalPrice(finalCost.floatValue());
			booking.setIsCancelled(false);
			booking.setLocalDateTime(LocalDateTime.now());
			booking.setTheatre(show.getTheatre().getId());
			booking.setShow(show.getId());
			booking.setMovie(show.getMovie().getId());
			booking.setStatus(BookingStatus.BOOKED.toString());
			booking.setSeats(bookingSeats.stream().map(seat -> seat.getId()).collect(Collectors.toList()));
			booking = bookingRepository.saveAndFlush(booking);
			Long bookingId = booking.getId();
			bookingSeats.stream().forEach(seat -> {
				seat.setBooked(true);
				seat.setBookingId(bookingId);
			});
			seatRepository.saveAll(bookingSeats);
			BeanUtils.copyProperties(booking, bookingDetailDto, "seats", "bookingRequest");
			bookingDetailDto.setSeats(bookingSeats.stream().map(seat -> TheatreMapper.toSeatDTO(seat)).collect(Collectors.toSet()));
			bookingDetailDto.setBookingRequest(request);
			initBookingDetailDTO(bookingDetailDto, customer, show);
		}
		logger.info("Booking exit");
		return retVal;
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.BookingService#bookingCancel(java.lang.String)
	 */
	@Override
	@Transactional
	public List<BookingDetailDTO> bookingCancel(String bookingIds) throws CommonAPIException {
		logger.info("Booking Cancellation entry");
		if(null == bookingIds || bookingIds.isEmpty()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		List<Long> ids = null;
		try {
			ids = Stream.of(bookingIds.split(",")).map(e -> Long.valueOf(e)).collect(Collectors.toList());
		}
		catch(NumberFormatException nfe) {
			throw new CommonAPIException(Exceptions.E0048);
		}
		List<Booking> bookings = bookingRepository.findAllById(ids);
		if(bookings.size() != ids.size()) {
			throw new CommonAPIException(Exceptions.E0049);
		}
		Map<String, Long> requestSummary = bookings.stream().collect(Collectors.groupingBy(Booking::getStatus, Collectors.counting()));
		if(requestSummary.containsKey("CANCELLED") || requestSummary.containsKey("FAILED")) {
			throw new CommonAPIException(Exceptions.E0050);
		}
		List<BookingDetailDTO> retVal = new ArrayList<>();
		for(Booking booking:bookings) {
			BookingDetailDTO bookingDetailDTO = new BookingDetailDTO();
			retVal.add(bookingDetailDTO);
			if(booking.getStatus().equalsIgnoreCase(BookingStatus.BOOKED.toString())) {
				booking.setStatus(BookingStatus.CANCELLED.toString());
				booking.setIsCancelled(true);
				Show show = showRepository.getOne(booking.getShow());
				Set<Seat> seats = show.getSeats();
				seats.forEach(seat -> {
					seat.setBookingId(null);
					seat.setBooked(false);
				});
				booking = bookingRepository.saveAndFlush(booking);
				showRepository.saveAndFlush(show);
				
				BeanUtils.copyProperties(booking, bookingDetailDTO, "seats", "bookingRequest");
				bookingDetailDTO.setMessage("Booking cancelled");
				bookingDetailDTO.setSeats(seats.stream().map(seat -> TheatreMapper.toSeatDTO(seat)).collect(Collectors.toSet()));
				initBookingDetailDTO(bookingDetailDTO, booking.getCustomer(), show);
			}
			else {
				BeanUtils.copyProperties(booking, bookingDetailDTO, "seats", "bookingRequest");
				bookingDetailDTO.setMessage("Cancellation not performed for this booking");
			}
		}
		logger.info("Booking Cancellation exit");
		return retVal;
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.BookingService#getBooking(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public List<BookingDetailDTO> getBooking(Long id, String city, String email, String movieName, String theatre, Long customerId, String mobile) throws CommonAPIException {
		logger.info("Get Booking entry");
		Set<Booking> bookings = new HashSet<>();
		if(null != id) {
			if(!bookingRepository.existsById(id)) {
				throw new CommonAPIException(Exceptions.E0049);
			}
			bookings.add(bookingRepository.getOne(id));
		}
		if(null != customerId) {
			if(!customerRepository.existsByCustomerId(customerId)) {
				throw new CommonAPIException(Exceptions.E0051);
			}
			bookings.addAll(bookingRepository.getBookingsByCustomer(customerId));
		}
		if(null != email && !email.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByCustomerEmail(email));
		}
		if(null != mobile && !mobile.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByCustomerMobileNo(mobile));
			bookings.addAll(bookingRepository.getBookingsByCustomerAltMobileNo(mobile));
		}
		if(null != city && !city.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByCustomerCity(city));
		}
		if(null != city && !city.isEmpty() && null != theatre && !theatre.isEmpty() && null != movieName && !movieName.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByCityTheatreMovie(city, theatre, movieName));
		}
		if(null != theatre && !theatre.isEmpty() && null != movieName && !movieName.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByTheatreMovie(theatre, movieName));
		}
		if(null != city && !city.isEmpty() && null != theatre && !theatre.isEmpty()) {
			bookings.addAll(bookingRepository.getBookingsByCityTheatre(city, theatre));
		}
		logger.info("Get Booking about exit");
		return bookings.stream().map(booking -> {
			BookingDetailDTO dto = new BookingDetailDTO();
			BeanUtils.copyProperties(booking, dto, "seats", "bookingRequest");
			if(showRepository.existsById(booking.getShow())) {
				Show show = showRepository.getOne(booking.getShow());
				Set<Seat> seats = show.getSeats().stream().filter(seat -> booking.getSeats().contains(seat.getId())).collect(Collectors.toSet());
				dto.setSeats(seats.stream().map(seat -> TheatreMapper.toSeatDTO(seat)).collect(Collectors.toSet()));
				initBookingDetailDTO(dto, booking.getCustomer(), show);
			}
			return dto;
		}).collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.BookingService#getByDetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<TicketDTO> getByDetails(String name, String city, String movie, String date) {
		logger.info("Get Ticket Details entry");
		LocalDate lDate = AppWebUtils.fromString(date);
		List<Show> shows = showRepository.getShowByTheatreOrCityOrMovieNameOrDate(name, city, movie, lDate);
		logger.info("Get Ticket Details about exit");
		return shows.stream().map(show -> {
			TicketDTO ticketDto = new TicketDTO();
			ticketDto.setMovie(TheatreMapper.toMovieDTO(show.getMovie()));
			ticketDto.setShow(TheatreMapper.toShowDTO(show, false));
			ticketDto.setTheatre(TheatreMapper.toTheatreDTO(show.getTheatre(), false));
			ticketDto.setSeats(TheatreMapper.toShowSeatsDTO(show.getSeats()));
			ticketDto.setOffers(offerRepository.getOfferByTheatreId(show.getTheatre().getId()).stream().map(e -> TheatreMapper.toOfferDTO(e)).collect(Collectors.toSet()));
			return ticketDto;
		}).collect(Collectors.toList());
	}

	/**
	 * Inits the booking detail DTO.
	 *
	 * @param dto      the dto
	 * @param customer the customer
	 * @param show     the show
	 * @return the booking detail DTO
	 */
	private BookingDetailDTO initBookingDetailDTO(BookingDetailDTO dto, Customer customer, Show show) {
		logger.info("BookingDetailDTO init started");
		if(dto.getStatus().equalsIgnoreCase(BookingStatus.BOOKED.toString()) || dto.getStatus().equalsIgnoreCase(BookingStatus.CANCELLED.toString())) {
			if(null != customer) {
				CustomerDTO customerDto = new CustomerDTO();
				BeanUtils.copyProperties(customer, customerDto);
				dto.setCustomer(customerDto);
			}
			if(null != show) {
				dto.setTheatre(TheatreMapper.toTheatreDTO(show.getTheatre(), false));
				dto.setShow(TheatreMapper.toShowDTO(show, false));
				dto.setMovie(TheatreMapper.toMovieDTO(show.getMovie()));
			}
		}
		logger.info("BookingDetailDTO init completed");
		return dto;
	}
	
	/**
	 * Calculate discunt.
	 *
	 * @param show          the show
	 * @param request       the request
	 * @param projectedCost the projected cost
	 * @return the big decimal
	 */
	private BigDecimal calculateDiscunt(Show show, BookingRequestDTO request, BigDecimal projectedCost) {
		logger.info("Discount calculation started");
		BigDecimal retVal = new BigDecimal(0);
		Set<Offer> offers = offerRepository.getOfferByTheatreId(show.getTheatre().getId());
		for(Offer offer:offers) {
			if(2 == offer.getDiscountType() && (null != offer.getTicketCount() && offer.getTicketCount() >= request.getSeats().size())) {
				int discountToBeGivenOnTickets = request.getSeats().size() / offer.getTicketCount();
				BigDecimal discountPerTicket = (new BigDecimal(show.getPrice()).multiply(new BigDecimal(offer.getPercentDiscount()))).divide(new BigDecimal(100));
				retVal = retVal.add(discountPerTicket.multiply(new BigDecimal(discountToBeGivenOnTickets)));
			}
			else if((1 == offer.getDiscountType() && show.getAfterNoon())) {
				retVal = retVal.add((projectedCost.multiply(new BigDecimal(offer.getPercentDiscount()))).divide(new BigDecimal(100)));
			}
		}
		logger.info("Discount calculation completed");
		return retVal;
	}

}
