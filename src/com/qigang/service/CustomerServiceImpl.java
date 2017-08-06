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
			throw new RuntimeException("�û����Ѿ�����");
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
	 * ͨ����������ɾ��
	 */
	@Override
	public void batchDelete(String[] ids) {
		Connection conn=DaoUtils.GetConn();
		try {
			//��������
			conn.setAutoCommit(false);
			
			for(String id:ids){
				dao.delCustomerWithTrans(conn,id);
			}

			//ִ�гɹ����ύ����
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			//ִ��ʧ�ܣ��ع�����
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Page getPagedCustomers(int pageIndex, int pageSize) {
		Page page=new Page();
		//��ǰҳ��
		page.setPageIndex(pageIndex);
		//һҳ�ж���������
		page.setPageSize(pageSize);
		
		//������
		int totalRowCount=dao.getTotalRowCont();
		page.setTotalRowCount(totalRowCount);
		
		//��ҳ��
		int pageCount=totalRowCount/pageSize+(totalRowCount%pageSize==0?0:1);
		page.setPageCount(pageCount);
		
		//��ҳ
		page.setFirstPage(1);
		//��һҳ
		page.setPrevPage(pageIndex<=1?1:(pageIndex-1));
		//��һҳ
		page.setNextPage(pageIndex>=pageCount?pageCount:(pageIndex+1));
		//βҳ
		page.setLastPage(pageCount);
		
		//��ǰҳ������
		List<Customer> list=dao.getPagedCustomers(pageIndex,pageSize);
		page.setList(list);
		
		return page;
	}
	
}
