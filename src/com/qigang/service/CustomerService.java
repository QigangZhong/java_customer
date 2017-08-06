package com.qigang.service;

import java.util.List;

import com.qigang.domain.Customer;
import com.qigang.domain.Page;

public interface CustomerService{

	/**
	 * 添加客户
	 * @param c
	 */
	void addCustomer(Customer c);

	List<Customer> getAllCustomers();

	Customer getCustomerById(String id);

	int updateCustomer(Customer cust);

	int deleteCustomer(String id);

	void batchDelete(String[] ids);

	/**
	 * 分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page getPagedCustomers(int pageIndex, int pageSize);

}
