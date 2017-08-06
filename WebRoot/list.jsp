<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
  	<script type="text/javascript">
  		function checkAll(ca){
  			var cs = document.getElementsByName("delId");
  			for(var i=0;i<cs.length;i++){
  				cs[i].checked=ca.checked;
  			}
  		}
  	</script>
  </head>
  
  <body>
    <h1>用户列表</h1>
    <form action="${pageContext.request.contextPath }/servlet/BatchDelServlet" method="POST">
	    <table border="1" width="100%">
	    	<tr><th><input type="checkbox" name="ca" onclick="checkAll(this)" />全选</th><th>姓名</th><th>性别</th><th>生日</th><th>手机</th><th>邮箱</th><th>爱好</th><th>类型</th><th>描述</th><th>操作</th></tr>
	    	<c:forEach items="${requestScope.list}" var="cust">
	    		<tr>
	    			<td><input type="checkbox" name="delId" value="${cust.id}" /></td>
	    			<td>${cust.name }</td>
	    			<td>${cust.gender }</td>
	    			<td>${cust.birthday }</td>
	    			<td>${cust.cellphone }</td>
	    			<td>${cust.email }</td>
	    			<td>${cust.preference }</td>
	    			<td>${cust.type }</td>
	    			<td>${cust.description }</td>
	    			<td>
	    				<a href="${pageContext.request.contextPath }/servlet/QueryToUpdateServlet?id=${cust.id}" >修改</a> | 
	    				<a href="${pageContext.request.contextPath }/servlet/DeleteServlet?id=${cust.id}" >删除</a>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    </table>
	    <input type="submit" value="批量删除" />
    </form>
  </body>
</html>
