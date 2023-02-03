/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.movie.entities.Language;

/**
 * The Interface LanguageRepository.
 * @author Sandeep Kumar
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {

	/**
	 * Gets the language count by code list.
	 *
	 * @param codes the codes
	 * @return the language count by code list
	 */
	@Query("select count(l) from Language l where l.code in (?1)")
	int getLanguageCountByCodeList(Set<String> codes);
	
	/**
	 * Gets the language by code list.
	 *
	 * @param codes the codes
	 * @return the language by code list
	 */
	@Query("select l from Language l where l.code in (?1)")
	Set<Language> getLanguageByCodeList(Set<String> codes);
}
