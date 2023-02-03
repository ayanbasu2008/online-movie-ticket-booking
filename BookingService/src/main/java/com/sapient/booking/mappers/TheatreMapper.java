/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.sapient.booking.entities.Movie;
import com.sapient.booking.entities.Offer;
import com.sapient.booking.entities.Seat;
import com.sapient.booking.entities.Show;
import com.sapient.booking.entities.Theatre;
import com.sapient.booking.models.MovieDTO;
import com.sapient.booking.models.OfferDTO;
import com.sapient.booking.models.SeatDTO;
import com.sapient.booking.models.ShowDTO;
import com.sapient.booking.models.ShowSeatsDTO;
import com.sapient.booking.models.TheatreDTO;

/**
 * The Class TheatreMapper.
 * @author Sandeep Kumar
 */
public final class TheatreMapper {
	
	/**
	 * Instantiates a new theatre mapper.
	 */
	private TheatreMapper() {}
	
	/**
	 * To theatre DTO.
	 *
	 * @param theatre the theatre
	 * @param deep    the deep
	 * @return the theatre DTO
	 */
	public static TheatreDTO toTheatreDTO(Theatre theatre, boolean deep) {
		if(null != theatre) {
			TheatreDTO theatreDto = new TheatreDTO();
			BeanUtils.copyProperties(theatre, theatreDto, "shows");
			if(null != theatre.getShows() && deep) {
				theatreDto.setShows(theatre.getShows()
						.stream()
						.map(e -> {
							return toShowDTO(e, true);
						})
						.collect(Collectors.toSet()));
			}
			return theatreDto;
		}
		return null;
	}
	
	/**
	 * To show DTO.
	 *
	 * @param show the show
	 * @param deep the deep
	 * @return the show DTO
	 */
	public static ShowDTO toShowDTO(Show show, boolean deep) {
		if(null != show) {
			ShowDTO showDto = new ShowDTO();
			BeanUtils.copyProperties(show, showDto, "theatre", "movie", "seats");
			if(deep) {
				showDto.setMovie(toMovieDTO(show.getMovie()));
				showDto.setSeats(toShowSeatsDTO(show.getSeats()));
			}
			return showDto;
		}
		return null;
	}
	
	/**
	 * To movie DTO.
	 *
	 * @param movie the movie
	 * @return the movie DTO
	 */
	public static MovieDTO toMovieDTO(Movie movie) {
		if(null != movie) {
			MovieDTO movieDto = new MovieDTO();
			BeanUtils.copyProperties(movie, movieDto, "genres", "languages");
			if(null != movie.getLanguages()) {
				movieDto.setLanguages(movie.getLanguages().stream().map(e -> e.getCode()).collect(Collectors.toSet()));
			}
			if(null != movie.getGenres()) {
				movieDto.setGenres(movie.getGenres().stream().map(e -> e.getName()).collect(Collectors.toSet()));
			}
			return movieDto;
		}
		return null;
	}
	
	/**
	 * To show seats DTO.
	 *
	 * @param seats the seats
	 * @return the show seats DTO
	 */
	public static ShowSeatsDTO toShowSeatsDTO(Set<Seat> seats) {
		if(null != seats) {
			ShowSeatsDTO showSeatsDTO = new ShowSeatsDTO();
			List<SeatDTO> totalSeats = seats.stream().map(e -> {
				SeatDTO seatDto = new SeatDTO();
				BeanUtils.copyProperties(e, seatDto, "show");
				seatDto.setShow(toShowDTO(e.getShow(), false));
				return seatDto;
				}).collect(Collectors.toList());
			List<SeatDTO> bookedSeats = totalSeats.stream().filter(e -> e.getBooked()).collect(Collectors.toList());
			List<SeatDTO> freeSeats = totalSeats.stream().filter(e -> !e.getBooked()).collect(Collectors.toList());;
			Integer totalSeatCount = totalSeats.size();
			Integer bookedSeatCount = bookedSeats.size();
			Integer freeSeatCount = freeSeats.size();
			showSeatsDTO.setBookedSeatCount(bookedSeatCount);
			showSeatsDTO.setBookedSeats(bookedSeats);
			showSeatsDTO.setFreeSeatCount(freeSeatCount);
			showSeatsDTO.setFreeSeats(freeSeats);
			showSeatsDTO.setTotalSeatCount(totalSeatCount);
			showSeatsDTO.setTotalSeats(totalSeats);
			return showSeatsDTO;
		}
		return null;
	}
	
	/**
	 * To offer DTO.
	 *
	 * @param offer the offer
	 * @return the offer DTO
	 */
	public static OfferDTO toOfferDTO(Offer offer) {
		if(null != offer) {
			OfferDTO offerDto = new OfferDTO();
			BeanUtils.copyProperties(offer, offerDto);
			return offerDto;
		}
		return null;
	}
	
	/**
	 * To seat DTO.
	 *
	 * @param seat the seat
	 * @return the seat DTO
	 */
	public static SeatDTO toSeatDTO(Seat seat) {
		if(null != seat) {
			SeatDTO seatDto = new SeatDTO();
			BeanUtils.copyProperties(seat, seatDto, "show");
			return seatDto;
		}
		return null;
	}
}
