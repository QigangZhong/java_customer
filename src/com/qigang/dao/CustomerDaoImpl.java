package com.qigang.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.qigang.domain.Customer;
import com.qigang.util.DaoUtils;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer findUserByName(String name) {
		
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.query("select * from customer where name=?", new BeanHandler<Customer>(Customer.class),name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int addCust(Customer c) {
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.update("insert into customer values(null,?,?,?,?,?,?,?,?)", c.getName(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
