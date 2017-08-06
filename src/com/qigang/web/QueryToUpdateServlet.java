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
 * 点击list.jsp中的修改按钮，调用此servlet查找到点击的用户，转发到update.jsp进行修改
 * @author Qigan
 *
 */
public class QueryToUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 获取要查询的id
		String id=request.getParameter("id");
		
		//2. 调用根据id查找用户的方法
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		Customer cust = service.getCustomerById(id);
		
		if(cust==null){
			throw new RuntimeException("找不到客户");
		}
		
		//3. 数据存储到request域中，请求转发到update.jsp
		request.setAttribute("cust", cust);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
