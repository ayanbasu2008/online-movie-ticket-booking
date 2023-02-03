/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

import com.sapient.customer.consts.Exceptions;
import com.sapient.customer.entities.Customer;
import com.sapient.customer.exceptions.CommonAPIException;
import com.sapient.customer.models.CustomerDTO;
import com.sapient.customer.repositories.BookingRepository;
import com.sapient.customer.repositories.CityRepository;
import com.sapient.customer.repositories.CustomerRepository;
import com.sapient.customer.services.CustomerService;
import com.sapient.customer.utils.SpecificationUtils;

/**
 * The Class CustomerServiceImpl.
 * @author Sandeep Kumar
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
	
	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;
	
	/** The booking repository. */
	@Autowired
	private BookingRepository bookingRepository;
	
	/** The city repository. */
	@Autowired
	private CityRepository cityRepository;

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.CustomerService#create(com.sapient.theatre.models.CustomerDTO)
	 */
	@Transactional
	@Override
	public CustomerDTO create(CustomerDTO customerDto) throws CommonAPIException {
		logger.info("Create customer started");
		if(null != customerDto.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		List<Customer> existingCustomers = customerRepository.getByEmailOrMobile(customerDto.getEmail(), customerDto.getMobileNo());
		if(existingCustomers.size() > 1) {
			throw new CommonAPIException(Exceptions.E0031);
		} else if(existingCustomers.size() == 1) {
			Customer customer = existingCustomers.get(0);
			if(customer.getActive()) {
				throw new CommonAPIException(Exceptions.E0028);
			}
			else {
				customer.setActive(true);
				customer = customerRepository.saveAndFlush(customer);
				BeanUtils.copyProperties(customer, customerDto);
				logger.info("Create customer end: case 1");
				return customerDto;
			}
		} else {
			if(!cityRepository.isCityAvailable(customerDto.getCity())) {
				throw new CommonAPIException(Exceptions.E0032);
			}
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDto, customer);
			customer.setActive(true);
			customer = customerRepository.saveAndFlush(customer);
			BeanUtils.copyProperties(customer, customerDto);
			logger.info("Create customer end: case 2");
			return customerDto;
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.CustomerService#update(com.sapient.theatre.models.CustomerDTO)
	 */
	@Transactional
	@Override
	public CustomerDTO update(CustomerDTO customerDto) throws CommonAPIException {
		logger.info("Update customer start");
		if(!customerRepository.existsByCustomerId(customerDto.getId())) {
			throw new CommonAPIException(Exceptions.E0033);
		}
		if(customerRepository.getByEmailAndNotId(customerDto.getId(), customerDto.getEmail())) {
			throw new CommonAPIException(Exceptions.E0034);
		}
		if(customerRepository.getByMobileNoAndNotId(customerDto.getId(), customerDto.getEmail())) {
			throw new CommonAPIException(Exceptions.E0035);
		}
		Customer customer = customerRepository.getOne(customerDto.getId());
		BeanUtils.copyProperties(customerDto, customer);
		customer.setActive(true);
		customer = customerRepository.saveAndFlush(customer);
		BeanUtils.copyProperties(customer, customerDto);
		logger.info("Update customer end");
		return customerDto;
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.CustomerService#delete(java.lang.Long)
	 */
	@Transactional
	@Override
	public CustomerDTO delete(Long id) throws CommonAPIException {
		logger.info("Delete customer start");
		if(!customerRepository.existsByCustomerId(id)) {
			throw new CommonAPIException(Exceptions.E0033);
		}
		CustomerDTO customerDto = new CustomerDTO();
		if(bookingRepository.getBookingByCustomer(id)) {
			if(bookingRepository.getBookingByCustomerFutureDated(id, LocalDateTime.now())) {
				throw new CommonAPIException(Exceptions.E0036);
			}
			Customer customer = customerRepository.getOne(id);
			customer.setActive(false);
			customer = customerRepository.saveAndFlush(customer);
			BeanUtils.copyProperties(customer, customerDto);
		}
		else {
			Customer customer = customerRepository.getOne(id);
			customerRepository.delete(customer);
			BeanUtils.copyProperties(customer, customerDto);
		}
		logger.info("Delete customer end");
		return customerDto;
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.CustomerService#getById(java.lang.Long)
	 */
	@Override
	public CustomerDTO getById(Long id) throws CommonAPIException {
		logger.info("GetByID customer start");
		if(!customerRepository.existsByCustomerId(id)) {
			throw new CommonAPIException(Exceptions.E0033);
		}
		Customer customer = customerRepository.getOne(id);
		CustomerDTO customerDto = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDto);
		logger.info("GetByID customer end");
		return customerDto;
	}

	/* (non-Javadoc)
	 * @see com.sapient.theatre.services.CustomerService#getAll(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<CustomerDTO> getAll(String query, Pageable pageable) {
		logger.info("GetAll customer start");
		Specification<Customer> specification = SpecificationUtils.buildSpecification(query, new ArrayList<>());
		Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
		List<CustomerDTO> customerDTOList = customerPage.getContent().stream().map(customer -> {
			CustomerDTO customerDto = new CustomerDTO();
			BeanUtils.copyProperties(customer, customerDto);
			return customerDto;
			}).collect(Collectors.toList());
		logger.info("GetAll customer end");
		return new PageImpl<CustomerDTO>(customerDTOList, pageable, customerPage.getTotalElements());
	}
}
