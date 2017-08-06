package com.qigang.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.qigang.dao.CustomerDao;
import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;
import com.qigang.util.DaoUtils;

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

	@Override
	public Customer getCustomerById(String id) {
		return dao.getCustomerById(id);
	}

	@Override
	public int updateCustomer(Customer cust) {
		return dao.updateCustomer(cust);
	}

	@Override
	public int deleteCustomer(String id) {
		return dao.deleteCustomer(id);
	}

	/**
	 * 通过事务，批量删除
	 */
	@Override
	public void batchDelete(String[] ids) {
		Connection conn=DaoUtils.GetConn();
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			for(String id:ids){
				dao.delCustomerWithTrans(conn,id);
			}
			
			int i=1/0;
			
			//执行成功，提交事务
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			//执行失败，回滚事务
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
