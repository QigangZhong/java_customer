package com.qigang.service;

import java.util.List;

import com.qigang.domain.Customer;

public interface CustomerService{

	/**
	 * Ìí¼Ó¿Í»§
	 * @param c
	 */
	void addCustomer(Customer c);

	List<Customer> getAllCustomers();

}
