<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FindAll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  
  <a href = "add.jsp">添加</a>  
  <h1>用户列表..........当前访问${countnum}次</h1>
    <table>
    <tr>
     <th>序号</th>
     <th>用户名</th>
     <th>密码</th>
     <th>邮箱</th>
     <th>操作</th>
    </tr>
    
     <c:forEach items="${list}" var="info" varStatus="status">
   <tr>
    <td>${status.index+1}</td>
    <td>${info.name}</td>
    <td>${info.password}</td>
    <td>${info.email}</td>
    <td> <a href="servlet/UpdateBlackList?id=${info.id}">加入黑名单</a></td>
  
    <td> <a href="servlet/DeleteServlet?id=${info.id}">删除</a> 
    <a href="servlet/FindByIdServlet?id=${info.id}">修改</a></td>
    
    </tr>
    </c:forEach>
    </table>
    
    
    <h1>黑名单列表</h1>
     <table>
    <tr>
     <th>序号</th>
     <th>用户名</th>
     <th>密码</th>
     <th>邮箱</th>
     <th>操作</th>
    </tr>
    
     <c:forEach items="${list1}" var="info" varStatus="status">
   <tr>
    <td>${status.index+1}</td>
    <td>${info.name}</td>
    <td>${info.password}</td>
    <td>${info.email}</td>
    <td> <a href="servlet/UpdateBlackList?id=${info.id}">移除黑名单</a></td>
    
    </tr>
    </c:forEach>
    </table>
  </body>
</html>
