package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);

		try {
			Customer c=new Customer();
			BeanUtils.populate(c, request.getParameterMap());
			
			String[] prefs=request.getParameterValues("preference");
			StringBuffer sb=new StringBuffer();
			for(String pref:prefs){
				sb.append(pref+",");
			}
			String p=sb.substring(0,sb.length()-1);
			c.setPreference(p);
			
			service.addCustomer(c);
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
