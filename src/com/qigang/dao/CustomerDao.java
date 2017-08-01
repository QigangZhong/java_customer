package com.qigang.dao;

import java.util.List;

import com.qigang.domain.Customer;

public interface CustomerDao {

	Customer findUserByName(String name);

	int addCust(Customer c);

	List<Customer> getAllCustomers();

}
