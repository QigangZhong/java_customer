<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
  	<script type="text/javascript">
  		function changePage(obj){
  			if(isNaN(obj.value)){
  				alert("请输入数字");
  				obj.value=${page.pageIndex}
  				return;
  			}else if(obj.value<=0 || obj.value>${page.pageCount}){
				alert("您输入的页码超过范围");
				obj.value=${page.pageIndex}
  				return;
			}else{
				window.location="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex="+obj.value;
			}
  		}
  	</script>
  </head>
  
  <body>
    <h1>分页查询用户数据</h1>
    <table border="1" width="100%">
    	<tr><th>姓名</th><th>性别</th><th>生日</th><th>手机</th><th>邮箱</th><th>爱好</th><th>类型</th><th>描述</th><th>操作</th></tr>
    	<c:forEach items="${requestScope.page.list}" var="cust">
    		<tr>
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
    共${page.totalRowCount }条记录/共${page.pageCount }页/当前第${page.pageIndex }页/
    <a href="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex=${page.firstPage}">首页</a>
    <a href="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex=${page.prevPage}">上一页</a>
    
    <c:if test="${page.pageCount<=5 }">
    	<c:set var="begin" value="1" scope="page"></c:set>
    	<c:set var="end" value="${page.pageCount }" scope="page"></c:set>
    </c:if>
    <c:if test="${page.pageCount>5 }">
    	<c:choose>
    		<c:when test="${page.pageIndex<=3 }">
    			<c:set var="begin" value="1" scope="page"></c:set>
    			<c:set var="end" value="5" scope="page"></c:set>
    		</c:when>
    		<c:when test="${page.pageIndex>=page.pageCount-2 }">
    			<c:set var="begin" value="${page.pageCount-4 }" scope="page"></c:set>
    			<c:set var="end" value="${page.pageCount }" scope="page"></c:set>
    		</c:when>
    		<c:otherwise>
  				<c:set var="begin" value="${page.pageIndex-2}" scope="page"></c:set>
  				<c:set var="end" value="${page.pageIndex+2}" scope="page"></c:set>
  			</c:otherwise>
    	</c:choose>
    </c:if>
    
    <c:forEach begin="${begin}" end="${end}" step="1" var="i">
  		<c:if test="${i == page.pageIndex}">
  			${i }
  		</c:if>
  		<c:if test="${i != page.pageIndex}">
  			<a href="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex=${i}">${i }</a>
  		</c:if>
  	</c:forEach>
    
    
    <a href="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex=${page.nextPage}">下一页</a>
    <a href="${pageContext.request.contextPath }/servlet/PagedListServlet?pageIndex=${page.lastPage}">尾页</a>
    跳转到第<input type="text" value="${page.pageIndex }" onchange="changePage(this)" style="width:40px" />页
  </body>
</html>
