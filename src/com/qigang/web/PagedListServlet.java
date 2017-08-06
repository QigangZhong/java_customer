package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qigang.domain.Page;
import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

public class PagedListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		
		//1. ��ȡ��ǰҪ��ʾ��ҳ���ÿҳ��ʾ�ļ�¼��
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize=5;
		
		//2. ����service�еķ�ҳ��ѯ����
		Page page=service.getPagedCustomers(pageIndex,pageSize);
		
		//3. ����request����,����pageList.jspҳ����չʾ
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pageList.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
