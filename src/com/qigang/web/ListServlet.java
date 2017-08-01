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
		
		//查询出所有用户
		List<Customer> customers= service.getAllCustomers();
		
		//设置到request域中，请求转发到list.jsp进行展示
		request.setAttribute("customers", customers);
		response.sendRedirect(request.getContextPath()+"/list.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
