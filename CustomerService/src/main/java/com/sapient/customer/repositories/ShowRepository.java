/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.customer.entities.Show;

/**
 * The Interface ShowRepository.
 * @author Sandeep Kumar
 */
public interface ShowRepository extends JpaRepository<Show, Long> {

	/**
	 * Checks if is show by show no.
	 *
	 * @param showNo the show no
	 * @return true, if is show by show no
	 */
	@Query("select case when count(s) > 0 then true else false end from Show s where s.showNo = ?1")
	boolean isShowByShowNo(String showNo);

	/**
	 * Checks if is show byid.
	 *
	 * @param id the id
	 * @return true, if is show byid
	 */
	@Query("select case when count(s) > 0 then true else false end from Show s where s.id = ?1")
	boolean isShowByid(Long id);
	
	/**
	 * Checks if is screen and local date and start time and end time.
	 *
	 * @param screen    the screen
	 * @param localDate the local date
	 * @param startTime the start time
	 * @param endTime   the end time
	 * @return true, if is screen and local date and start time and end time
	 */
	@Query("select case when count(s) > 0 then true else false end from Show s where s.screen = ?1 and s.localDate = ?2 and ((s.fromTime between ?3 and ?4) or (s.toTime between ?3 and ?4))")
	boolean isScreenAndLocalDateAndStartTimeAndEndTime(String screen, LocalDate localDate, LocalTime startTime, LocalTime endTime);
	
	/**
	 * Checks if is show by movie.
	 *
	 * @param id the id
	 * @return true, if is show by movie
	 */
	@Query("select case when count(s) > 0 then true else false end from Show s where s.movie.id = ?1")
	boolean isShowByMovie(Long id);
	
	/**
	 * Gets the shows by theatre.
	 *
	 * @param id the id
	 * @return the shows by theatre
	 */
	@Query("select s from Show s where s.theatre.id = ?1")
	Set<Show> getShowsByTheatre(Long id);
	
	/**
	 * Gets the show by theatre or city or movie name or date.
	 *
	 * @param theatre the theatre
	 * @param city    the city
	 * @param movie   the movie
	 * @param date    the date
	 * @return the show by theatre or city or movie name or date
	 */
	@Query("from Show s where 1 = 1 and ((?1 is not null and s.theatre.name = ?1) or (?2 is not null and s.theatre.city = ?2) or (?3 is not null and s.movie.name = ?3) or (?4 is not null and s.localDate = ?4))")
	List<Show> getShowByTheatreOrCityOrMovieNameOrDate(String theatre, String city, String movie, LocalDate date);

	/**
	 * Gets the show by theatre or city or show or movie name.
	 *
	 * @param theatre the theatre
	 * @param city    the city
	 * @param showno  the showno
	 * @param movie   the movie
	 * @return the show by theatre or city or show or movie name
	 */
	@Query("from Show s where 1 = 1 and (s.theatre.name = ?1 or s.theatre.city = ?2 or s.showNo = ?3 or s.movie.name = ?4)")
	List<Show> getShowByTheatreOrCityOrShowOrMovieName(String theatre, String city, String showno, String movie);

	/**
	 * Gets the show by theatre or city or movie id.
	 *
	 * @param theatre the theatre
	 * @param show    the show
	 * @param movie   the movie
	 * @return the show by theatre or city or movie id
	 */
	@Query("from Show s where 1 = 1 and (s.theatre.id = ?1 or s.id = ?2 or s.movie.id = ?3)")
	List<Show> getShowByTheatreOrCityOrMovieId(Long theatre, Long show, Long movie);

}
