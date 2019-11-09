<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"

	import="model.User"

%>

<%
	User user = (User) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {
		session.removeAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>ログアウト</title>
</head>
<body>
	ログアウトしました
	<br><br>
<input type="button" value="ログイン画面に戻る"
onclick="location.href='login.jsp'" class="btn btn-default">
</body>
</html>
<% } %>