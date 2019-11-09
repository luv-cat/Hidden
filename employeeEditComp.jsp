<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.EmployeeBean"%>

<%
//更新前データをセッションから取得
EmployeeBean before=(EmployeeBean)session.getAttribute("editData");
//更新後データをセッションから取得
EmployeeBean after=(EmployeeBean)session.getAttribute("editDataUpdate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>編集完了</title>
</head>
<body class="block container-fluid">
<%@ include file="header.jsp"%>
<h1>編集完了</h1>
<br>
編集を完了しました<br>
<input type="button" value="一覧表示に戻る" onclick="location.href='ShowAllList'" class="btn btn-default">
<!--<form action="employeeDisplay.jsp" method="get">
</form>  -->
</body>
</html>