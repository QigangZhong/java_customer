package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

/**
 * ���list.jsp�е��޸İ�ť�����ô�servlet���ҵ�������û���ת����update.jsp�����޸�
 * @author Qigan
 *
 */
public class QueryToUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. ��ȡҪ��ѯ��id
		String id=request.getParameter("id");
		
		//2. ���ø���id�����û��ķ���
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		Customer cust = service.getCustomerById(id);
		
		if(cust==null){
			throw new RuntimeException("�Ҳ����ͻ�");
		}
		
		//3. ���ݴ洢��request���У�����ת����update.jsp
		request.setAttribute("cust", cust);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
