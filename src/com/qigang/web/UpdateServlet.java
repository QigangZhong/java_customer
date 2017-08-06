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
			//1. �����޸�ҳ�洫�ݹ��������ݣ���װ��bean
			Customer cust=new Customer();
			BeanUtils.populate(cust,request.getParameterMap());
			
			//2. ��������preference�ֶ�ֵ
			String[] prefs=request.getParameterValues("preference");
			StringBuffer sb=new StringBuffer();
			for(String pref:prefs){
				sb.append(pref+",");
			}
			String p=sb.substring(0,sb.length()-1);
			cust.setPreference(p);
			
			//3. ����service�������¿ͻ���Ϣ
			service.updateCustomer(cust);
			
			//4. �ض����б�ҳ��
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
