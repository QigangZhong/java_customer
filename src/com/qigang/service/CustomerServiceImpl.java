package com.qigang.service;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.qigang.dao.CustomerDao;
import com.qigang.domain.Customer;
import com.qigang.domain.Page;
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

			//执行成功，提交事务
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			//执行失败，回滚事务
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Page getPagedCustomers(int pageIndex, int pageSize) {
		Page page=new Page();
		//当前页码
		page.setPageIndex(pageIndex);
		//一页有多少行数据
		page.setPageSize(pageSize);
		
		//总行数
		int totalRowCount=dao.getTotalRowCont();
		page.setTotalRowCount(totalRowCount);
		
		//总页数
		int pageCount=totalRowCount/pageSize+(totalRowCount%pageSize==0?0:1);
		page.setPageCount(pageCount);
		
		//首页
		page.setFirstPage(1);
		//上一页
		page.setPrevPage(pageIndex<=1?1:(pageIndex-1));
		//下一页
		page.setNextPage(pageIndex>=pageCount?pageCount:(pageIndex+1));
		//尾页
		page.setLastPage(pageCount);
		
		//当前页的数据
		List<Customer> list=dao.getPagedCustomers(pageIndex,pageSize);
		page.setList(list);
		
		return page;
	}
	
}
