<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.User, model.EmployeeBean"
%>
<%
	String employeeCode = request.getParameter("employeeCode");
	EmployeeBean e =(EmployeeBean)session.getAttribute("editData");
	User user = (User)session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {
%>
<%//デバッグ用

if(employeeCode ==null){
	employeeCode = "E0001"; }
System.out.println(employeeCode);%>
<!--

/*2019/03/05
 * auther: <hidden>
 * 削除確認jsp
 *
 * */

 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp" %>
<title>削除確認画面</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class = "container-fluid">

<form action="DeleteCheck" class ="inline">


<div class = "text-center">
<h3>従業員コード:<%= e.getEmployeeCode() %>    <%=e.getLastName() %> <%= e.getFirstName() %></h3><br>
<h4>上記の従業員情報を削除してよろしいですか？</h4>
<br>

<input type ="button" value ="キャンセル" class="btn btn-default" onclick="location.href='ShowAllList'">
<input type ="submit" value = "削除" class="btn btn-danger">

<!-- 以下に社員コードを設定 -->
<input type ="hidden"  name ="id" value ="<%= e.getEmployeeCode() %>">

</div>


</form>
</div>


</body>
</html>
<% } %>