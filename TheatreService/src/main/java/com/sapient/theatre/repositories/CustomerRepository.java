/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sapient.theatre.entities.Customer;

/**
 * The Interface CustomerRepository.
 * @author Sandeep Kumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	/**
	 * Gets the by email or mobile.
	 *
	 * @param email    the email
	 * @param mobileNo the mobile no
	 * @return the by email or mobile
	 */
	@Query("select c from Customer c where c.email = ?1 or c.mobileNo = ?2")
	List<Customer> getByEmailOrMobile(String email, String mobileNo);

	/**
	 * Gets the by email and not id.
	 *
	 * @param id    the id
	 * @param email the email
	 * @return the by email and not id
	 */
	@Query("select case when count(c) > 0 then true else false end from Customer c where c.id <> ?1 and c.email = ?2")
	boolean getByEmailAndNotId(Long id, String email);

	/**
	 * Gets the by mobile no and not id.
	 *
	 * @param id       the id
	 * @param mobileNo the mobile no
	 * @return the by mobile no and not id
	 */
	@Query("select case when count(c) > 0 then true else false end from Customer c where c.id <> ?1 and c.mobileNo = ?2")
	boolean getByMobileNoAndNotId(Long id, String mobileNo);
	
	/**
	 * Exists by customer id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Query("select case when count(c) > 0 then true else false end from Customer c where c.id = ?1")
	boolean existsByCustomerId(Long id);

	/**
	 * Exists by customer id and active.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Query("select case when count(c) > 0 then true else false end from Customer c where c.id = ?1 and c.active = true")
	boolean existsByCustomerIdAndActive(Long id);
}
