package com.qigang.dao;

import com.qigang.domain.Customer;

public interface CustomerDao {

	Customer findUserByName(String name);

	int addCust(Customer c);

}
