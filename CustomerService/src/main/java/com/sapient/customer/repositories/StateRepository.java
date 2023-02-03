/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.customer.entities.State;

/**
 * The Interface StateRepository.
 * @author Sandeep Kumar
 */
public interface StateRepository extends JpaRepository<State, Long>{

}
