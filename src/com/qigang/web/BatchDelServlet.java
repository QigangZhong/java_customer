package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

public class BatchDelServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		
		//1. 获取要批量删除的id列表
		String[] ids=request.getParameterValues("delId");
		
		//2. 调用servie批量删除方法
		service.batchDelete(ids);
		
		//3. 重定向到客户列表页面
		request.getRequestDispatcher("/servlet/ListServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
