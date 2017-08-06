<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    
  </head>
  
  <body>
    <h1>客户管理系统</h1>
    <a  href="${pageContext.request.contextPath}/add.jsp">添加客户</a><br>
    <a  href="${pageContext.request.contextPath}/servlet/ListServlet">列表</a><br>
    <a  href="${pageContext.request.contextPath}/servlet/PagedListServlet?pageIndex=1">分页后的用户列表</a>
  </body>
</html>
