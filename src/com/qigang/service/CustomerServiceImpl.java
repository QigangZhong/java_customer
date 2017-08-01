package com.qigang.service;

import java.util.List;

import com.qigang.dao.CustomerDao;
import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;

public class CustomerServiceImpl implements CustomerService{
	CustomerDao dao = BasicFactory.getFactory().getInstance(CustomerDao.class);

	@Override
	public void addCustomer(Customer c) {
		if(dao.findUserByName(c.getName())!=null){
			throw new RuntimeException("用户名已经存在");
		}
		
		dao.addCust(c);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return dao.getAllCustomers();
	}
	
}
