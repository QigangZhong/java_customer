package com.qigang.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.qigang.domain.Customer;

public interface CustomerDao {

	Customer findUserByName(String name);

	int addCust(Customer c);

	List<Customer> getAllCustomers();

	Customer getCustomerById(String id);

	int updateCustomer(Customer cust);

	int deleteCustomer(String id);

	void delCustomerWithTrans(Connection conn, String id)  throws SQLException;

}
