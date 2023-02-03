/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.theatre.consts.Exceptions;
import com.sapient.theatre.entities.Movie;
import com.sapient.theatre.entities.Seat;
import com.sapient.theatre.entities.Show;
import com.sapient.theatre.entities.Theatre;
import com.sapient.theatre.exceptions.CommonAPIException;
import com.sapient.theatre.mappers.TheatreMapper;
import com.sapient.theatre.models.SeatDTO;
import com.sapient.theatre.models.ShowDTO;
import com.sapient.theatre.models.ShowSeatsDTO;
import com.sapient.theatre.models.TheatreDTO;
import com.sapient.theatre.repositories.BookingRepository;
import com.sapient.theatre.repositories.CityRepository;
import com.sapient.theatre.repositories.MovieRepository;
import com.sapient.theatre.repositories.SeatRepository;
import com.sapient.theatre.repositories.ShowRepository;
import com.sapient.theatre.repositories.TheatreRepository;
import com.sapient.theatre.services.TheatreService;
import com.sapient.theatre.utils.SpecificationUtils;
import com.sapient.theatre.utils.TheatreUtils;

/**
 * The Class TheatreServiceImpl.
 * @author Sandeep Kumar
 */
@Service
public class TheatreServiceImpl implements TheatreService {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(TheatreServiceImpl.class);
	
	/** The theatre repository. */
	@Autowired
	private TheatreRepository theatreRepository;
	
	/** The city repository. */
	@Autowired
	private CityRepository cityRepository;
	
	/** The movie repository. */
	@Autowired
	private MovieRepository movieRepository;
	
	/** The show repository. */
	@Autowired
	private ShowRepository showRepository;
	
	/** The booking repository. */
	@Autowired
	private BookingRepository bookingRepository;
	
	/** The seat repository. */
	@Autowired
	private SeatRepository seatRepository;

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#create(com.sapient.theatre.models.TheatreDTO)
	 */
	@Override
	@Transactional
	public TheatreDTO create(TheatreDTO theatreDto) throws CommonAPIException {
		logger.info("Theatre creation started");
		if(null != theatreDto.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!cityRepository.isCityAvailable(theatreDto.getCity())) {
			throw new CommonAPIException(Exceptions.E0032);
		}
		if(theatreRepository.isTheatreByNameAndCity(theatreDto.getName(), theatreDto.getCity())) {
			Theatre theatre = theatreRepository.getTheatreByNameAndCity(theatreDto.getName(), theatreDto.getCity());
			if(theatre.getActive()) {
				throw new CommonAPIException(Exceptions.E0026);
			}
			theatre.setActive(true);
			theatre = theatreRepository.saveAndFlush(theatre);
			theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
			logger.info("Theatre creation ended: case 1");
			return TheatreMapper.toTheatreDTO(theatre, true);
		} else {
			Theatre theatre = new Theatre();
			BeanUtils.copyProperties(theatreDto, theatre, "shows");
			theatre = theatreRepository.saveAndFlush(theatre);
			theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
			logger.info("Theatre creation ended: case 2");
			return TheatreMapper.toTheatreDTO(theatre, true);
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#update(com.sapient.theatre.models.TheatreDTO)
	 */
	@Override
	@Transactional
	public TheatreDTO update(TheatreDTO theatreDto) throws CommonAPIException {
		logger.info("Theatre update started");
		if(null == theatreDto.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!cityRepository.isCityAvailable(theatreDto.getCity())) {
			throw new CommonAPIException(Exceptions.E0032);
		}
		if(theatreRepository.isTheatreByNameAndCityExceptId(theatreDto.getName(), theatreDto.getCity(), theatreDto.getId())) {
			throw new CommonAPIException(Exceptions.E0026);
		}
		Theatre theatre = theatreRepository.getOne(theatreDto.getId());
		BeanUtils.copyProperties(theatreDto, theatre, "shows");
		theatre.setActive(true);
		theatre = theatreRepository.saveAndFlush(theatre);
		theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
		logger.info("Theatre update ended");
		return TheatreMapper.toTheatreDTO(theatre, true);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public TheatreDTO delete(Long id) throws CommonAPIException {
		logger.info("Theatre delete started");
		if(null == id) {
			throw new CommonAPIException(Exceptions.E0040);
		}
		Theatre theatre = theatreRepository.getOne(id);
		if(null != theatre.getShows() && !theatre.getShows().isEmpty()) {
			theatre.setActive(false);
			theatre = theatreRepository.saveAndFlush(theatre);
		}
		else {
			theatreRepository.delete(theatre);
		}
		logger.info("Theatre delete ended");
		return TheatreMapper.toTheatreDTO(theatre, false);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#getById(java.lang.Long)
	 */
	@Override
	public TheatreDTO getById(Long id) throws CommonAPIException {
		logger.info("Theatre getById started");
		if(theatreRepository.isTheatreById(id)) {
			Theatre theatre = theatreRepository.getOne(id);
			theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
			logger.info("Theatre getById ended");
			return TheatreMapper.toTheatreDTO(theatre, true);
		}
		else {
			logger.info("Theatre getById given id not available");
			throw new CommonAPIException(Exceptions.E0040);
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#getAll(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<TheatreDTO> getAll(String query, Pageable pageable) {
		logger.info("Theatre getAll started");
		Specification<Theatre> specification = SpecificationUtils.buildSpecification(query, new ArrayList<>());
		Page<Theatre> theatrePage = theatreRepository.findAll(specification, pageable);
		List<TheatreDTO> theatreDTOList = theatrePage.getContent().stream().map(theatre -> {
			theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
			return TheatreMapper.toTheatreDTO(theatre, true);
			}).collect(Collectors.toList());
		logger.info("Theatre getAll ended");
		return new PageImpl<TheatreDTO>(theatreDTOList, pageable, theatrePage.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#createShow(com.sapient.theatre.models.ShowDTO)
	 */
	@Override
	@Transactional
	public TheatreDTO createShow(ShowDTO showDto) throws CommonAPIException {
		logger.info("Create show in theatre started");
		if(null != showDto.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!theatreRepository.isTheatreById(showDto.getTheatreId())) {
			throw new CommonAPIException(Exceptions.E0040);
		}
		if(!movieRepository.isMovieById(showDto.getMovieId())) {
			throw new CommonAPIException(Exceptions.E0037);
		}
		if(showRepository.isShowByShowNo(showDto.getShowNo())) {
			throw new CommonAPIException(Exceptions.E0041);
		}
		if(showRepository.isScreenAndLocalDateAndStartTimeAndEndTime(showDto.getScreen(), showDto.getLocalDate(), showDto.getFromTime(), showDto.getToTime())) {
			throw new CommonAPIException(Exceptions.E0039);
		}
		Theatre theatre = theatreRepository.getOne(showDto.getTheatreId());
		Movie movie = movieRepository.getOne(showDto.getMovieId());
		Set<Seat> seats = TheatreUtils.generateSeats(theatre.getRows(), theatre.getColumns());
		List<Seat> seatList = seatRepository.saveAll(seats);
		seats = seatList.stream().collect(Collectors.toSet());
		Show show = new Show();
		BeanUtils.copyProperties(showDto, show, "seats", "movie", "theatre");
		show.setMovie(movie);
		show.setSeats(seats);
		show.setTheatre(theatre);
		show = showRepository.saveAndFlush(show);
		theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
		logger.info("Create show in theatre ended");
		return TheatreMapper.toTheatreDTO(theatre, true);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#deleteShow(java.lang.Long)
	 */
	@Override
	@Transactional
	public TheatreDTO deleteShow(Long id) throws CommonAPIException {
		logger.info("Delete show in theatre started");
		if(null == id) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!showRepository.isShowByid(id)) {
			throw new CommonAPIException(Exceptions.E0043);
		}
		if(bookingRepository.getBookingByShow(id)) {
			throw new CommonAPIException(Exceptions.E0042);
		}
		Show show = showRepository.getOne(id);
		Theatre theatre = show.getTheatre();
		show.setMovie(null);
		show = showRepository.saveAndFlush(show);
		showRepository.delete(show);
		theatre.getShows().addAll(showRepository.getShowsByTheatre(theatre.getId()));
		logger.info("Delete show in theatre ended");
		return TheatreMapper.toTheatreDTO(theatre, true);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.TheatreService#udpateShow(com.sapient.theatre.models.ShowSeatsDTO)
	 */
	@Override
	@Transactional
	public ShowSeatsDTO udpateShow(ShowSeatsDTO dto) throws CommonAPIException {
		logger.info("Update show in theatre started");
		if(null == dto) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!showRepository.isShowByid(dto.getShowId())) {
			throw new CommonAPIException(Exceptions.E0043);
		}
		Show show = showRepository.getOne(dto.getShowId());
		Set<Seat> seats = show.getSeats();
		List<String> availableSeatCodes = seats.stream().map(Seat::getCode).collect(Collectors.toList());
		List<String> bookedSeatCodes = seats.stream().filter(e -> e.getBooked()).map(Seat::getCode).collect(Collectors.toList());
		if(null != dto.getToBeAdded()) {
			for(SeatDTO seatDto:dto.getToBeAdded()) {
				if(availableSeatCodes.contains(seatDto.getCode())) {
					throw new CommonAPIException(Exceptions.E0045);
				}
				else {
					Seat seat = new Seat();
					seat.setBooked(false);
					seat.setCode(seatDto.getCode());
					seat.setShow(show);
					seats.add(seat);
				}
			}
		}
		if(null != dto.getToBeRemoved()) {
			List<String> seatCodesToBeRemoved = dto.getToBeRemoved().stream().map(SeatDTO::getCode).collect(Collectors.toList());
			List<String> seatToBeRemovedAndAlreadyBooked = seatCodesToBeRemoved.stream().filter(e -> bookedSeatCodes.contains(e)).collect(Collectors.toList());
			if(!seatToBeRemovedAndAlreadyBooked.isEmpty()) {
				throw new CommonAPIException(Exceptions.E0044);
			}
			Set<Seat> revisedSeats = seats.stream().filter(e -> !seatCodesToBeRemoved.contains(e.getCode())).collect(Collectors.toSet());
			Set<Seat> removeSeats = seats.stream().filter(e -> seatCodesToBeRemoved.contains(e.getCode())).map(e -> {e.setShow(null); return e;}).collect(Collectors.toSet());
			List<Seat> deleteSeats = seatRepository.saveAll(removeSeats);
			seatRepository.deleteAll(deleteSeats);
			show.setSeats(revisedSeats);
		}
		show = showRepository.saveAndFlush(show);
		logger.info("Update show in theatre ended");
		return TheatreMapper.toShowDTO(show, true).getSeats();
	}

	
}
