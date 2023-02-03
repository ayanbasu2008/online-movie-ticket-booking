/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.services.impl;

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

import com.sapient.movie.consts.Exceptions;
import com.sapient.movie.entities.Genre;
import com.sapient.movie.entities.Language;
import com.sapient.movie.entities.Movie;
import com.sapient.movie.exceptions.CommonAPIException;
import com.sapient.movie.mappers.TheatreMapper;
import com.sapient.movie.models.MovieDTO;
import com.sapient.movie.repositories.GenreRepository;
import com.sapient.movie.repositories.LanguageRepository;
import com.sapient.movie.repositories.MovieRepository;
import com.sapient.movie.repositories.ShowRepository;
import com.sapient.movie.services.MovieService;
import com.sapient.movie.utils.SpecificationUtils;

/**
 * The Class MovieServiceImpl.
 * @author Sandeep Kumar
 */
@Service
public class MovieServiceImpl implements MovieService {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
	
	/** The movie repository. */
	@Autowired
	private MovieRepository movieRepository;
	
	/** The genre repository. */
	@Autowired
	private GenreRepository genreRepository;
	
	/** The language repository. */
	@Autowired
	private LanguageRepository languageRepository;
	
	/** The show repository. */
	@Autowired
	private ShowRepository showRepository;

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.MovieService#create(com.sapient.theatre.models.MovieDTO)
	 */
	@Override
	@Transactional
	public MovieDTO create(MovieDTO movieDto) throws CommonAPIException {
		logger.info("Movie creation started");
		if(null != movieDto.getId() || null == movieDto.getGenres() || null == movieDto.getLanguages() || movieDto.getGenres().isEmpty() || movieDto.getLanguages().isEmpty()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		int genreCount = genreRepository.getGenreCountByNameList(movieDto.getGenres());
		if(genreCount != movieDto.getGenres().size()) {
			throw new CommonAPIException(Exceptions.E0046);
		}
		int languageCount = languageRepository.getLanguageCountByCodeList(movieDto.getLanguages());
		if(languageCount != movieDto.getLanguages().size()) {
			throw new CommonAPIException(Exceptions.E0047);
		}
		if(movieRepository.isMovieByNameAndYear(movieDto.getName(), movieDto.getYear())) {
			throw new CommonAPIException(Exceptions.E0029);
		}
		Set<Language> languages = languageRepository.getLanguageByCodeList(movieDto.getLanguages());
		Set<Genre> genres = genreRepository.getGenreByNameList(movieDto.getGenres());
		
		Movie movie = new Movie();
		BeanUtils.copyProperties(movieDto, movie, "genres", "languages");
		movie.setActive(true);
		movie.setGenres(genres);
		movie.setLanguages(languages);
		movie = movieRepository.saveAndFlush(movie);
		logger.info("Movie creation completed");
		return TheatreMapper.toMovieDTO(movie);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.MovieService#update(com.sapient.theatre.models.MovieDTO)
	 */
	@Override
	@Transactional
	public MovieDTO update(MovieDTO movieDto) throws CommonAPIException {
		logger.info("Movie update started");
		if(null == movieDto.getId() || null == movieDto.getGenres() || null == movieDto.getLanguages() || movieDto.getGenres().isEmpty() || movieDto.getLanguages().isEmpty()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!movieRepository.isMovieById(movieDto.getId())) {
			throw new CommonAPIException(Exceptions.E0037);
		}
		int genreCount = genreRepository.getGenreCountByNameList(movieDto.getGenres());
		if(genreCount != movieDto.getGenres().size()) {
			throw new CommonAPIException(Exceptions.E0046);
		}
		int languageCount = languageRepository.getLanguageCountByCodeList(movieDto.getLanguages());
		if(languageCount != movieDto.getLanguages().size()) {
			throw new CommonAPIException(Exceptions.E0047);
		}
		if(movieRepository.isMovieByNameAndYearWithoutSameId(movieDto.getId(), movieDto.getName(), movieDto.getYear())) {
			throw new CommonAPIException(Exceptions.E0029);
		}
		Set<Language> languages = languageRepository.getLanguageByCodeList(movieDto.getLanguages());
		Set<Genre> genres = genreRepository.getGenreByNameList(movieDto.getGenres());

		Movie movie = movieRepository.getOne(movieDto.getId());
		BeanUtils.copyProperties(movieDto, movie, "genres", "languages");
		movie.setActive(true);
		movie.setGenres(genres);
		movie.setLanguages(languages);
		movie = movieRepository.saveAndFlush(movie);
		logger.info("Movie update completed");
		return TheatreMapper.toMovieDTO(movie);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.MovieService#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public MovieDTO delete(Long id) throws CommonAPIException {
		logger.info("Movie delete started");
		if(null == id) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(!movieRepository.isMovieById(id)) {
			throw new CommonAPIException(Exceptions.E0037);
		}
		if(showRepository.isShowByMovie(id)) {
			Movie movie = movieRepository.getOne(id);
			movie.setActive(false);
			movie = movieRepository.save(movie);
			return TheatreMapper.toMovieDTO(movie);
		}
		Movie movie = movieRepository.getOne(id);
		movie.setGenres(null);
		movie.setLanguages(null);
		movie = movieRepository.save(movie);
		movieRepository.delete(movie);
		logger.info("Movie delete completed");
		return TheatreMapper.toMovieDTO(movie);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.MovieService#getById(java.lang.Long)
	 */
	@Override
	public MovieDTO getById(Long id) throws CommonAPIException {
		logger.info("Movie GETBYID started");
		if(!movieRepository.isMovieById(id)) {
			throw new CommonAPIException(Exceptions.E0037);
		}
		Movie movie = movieRepository.getOne(id);
		logger.info("Movie GETBYID completed");
		return TheatreMapper.toMovieDTO(movie);
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.MovieService#getAll(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<MovieDTO> getAll(String query, Pageable pageable) {
		logger.info("Movie GETALL started");
		Specification<Movie> specification = SpecificationUtils.buildSpecification(query, new ArrayList<>());
		Page<Movie> moviePage = movieRepository.findAll(specification, pageable);
		List<MovieDTO> movieDTOList = moviePage.getContent().stream().map(movie -> {
			return TheatreMapper.toMovieDTO(movie);
			}).collect(Collectors.toList());
		logger.info("Movie GETALL completed");
		return new PageImpl<MovieDTO>(movieDTOList, pageable, moviePage.getTotalElements());
	}


}
