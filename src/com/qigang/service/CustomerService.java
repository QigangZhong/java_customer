package com.qigang.service;

import java.util.List;

import com.qigang.domain.Customer;

public interface CustomerService{

	/**
	 * ��ӿͻ�
	 * @param c
	 */
	void addCustomer(Customer c);

	List<Customer> getAllCustomers();

}
