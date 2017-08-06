package com.qigang.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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
	public Customer getCustomerById(String id) {
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.query("select * from customer where id=?", new BeanHandler<Customer>(Customer.class),id);
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

	@Override
	public List<Customer> getAllCustomers() {
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			List<Customer> list = runner.query("select * from customer", new BeanListHandler<Customer>(Customer.class));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int updateCustomer(Customer c) {
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.update("update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?", c.getName(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription(),c.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int deleteCustomer(String id) {
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.update("delete from customer where id=?",id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delCustomerWithTrans(Connection conn, String id) throws SQLException {
		QueryRunner runner=new QueryRunner();
		runner.update(conn, "delete from customer where id=?",id);
	}

	@Override
	public int getTotalRowCont() {
		
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			Long count = (Long)runner.query("select count(*) from customer", new ScalarHandler());
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Customer> getPagedCustomers(int pageIndex, int pageSize) {
		try{
			QueryRunner runner=new QueryRunner(DaoUtils.GetDataSource());
			return runner.query("select * from customer limit ?,?", new BeanListHandler<Customer>(Customer.class),(pageIndex-1)*pageSize,pageSize);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
