<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.EmployeeBean"%>

<%
EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");

if(emp==null){
	session.setAttribute("directAccess", "directAccess");
	response.sendRedirect("MessageLogin.jsp");
}else{
session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>ログアウト</title>
</head>
<body class ="block container-fluid">
ログアウトしました<br/>
<input type="button" onclick="location.href='MessageLogin.jsp'" value="ログイン画面に戻る" class="btn btn-default">
</body>
</html>
<% } %>