package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		
		//��ѯ�������û�
		List<Customer> customers= service.getAllCustomers();
		
		//���õ�request���У�����ת����list.jsp����չʾ
		request.setAttribute("customers", customers);
		response.sendRedirect(request.getContextPath()+"/list.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
