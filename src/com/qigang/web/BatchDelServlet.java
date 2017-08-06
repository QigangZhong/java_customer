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
		
		//1. ��ȡҪ����ɾ����id�б�
		String[] ids=request.getParameterValues("delId");
		
		//2. ����servie����ɾ������
		service.batchDelete(ids);
		
		//3. �ض��򵽿ͻ��б�ҳ��
		request.getRequestDispatcher("/servlet/ListServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
