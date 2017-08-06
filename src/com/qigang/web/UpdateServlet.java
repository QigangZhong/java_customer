package com.qigang.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qigang.domain.Customer;
import com.qigang.factory.BasicFactory;
import com.qigang.service.CustomerService;

public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CustomerService service=BasicFactory.getFactory().getInstance(CustomerService.class);
		
		try {
			//1. 接收修改页面传递过来的数据，封装成bean
			Customer cust=new Customer();
			BeanUtils.populate(cust,request.getParameterMap());
			
			//2. 单独处理preference字段值
			String[] prefs=request.getParameterValues("preference");
			StringBuffer sb=new StringBuffer();
			for(String pref:prefs){
				sb.append(pref+",");
			}
			String p=sb.substring(0,sb.length()-1);
			cust.setPreference(p);
			
			//3. 调用service方法更新客户信息
			service.updateCustomer(cust);
			
			//4. 重定向到列表页面
			response.sendRedirect(request.getContextPath()+"/servlet/ListServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
