<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String status=(String)session.getAttribute("passErr");
String directAccess=(String)session.getAttribute("directAccess");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="boot.jsp"  %>
<title>＜hidden＞にログイン</title>
</head>

<body class="text-center">

	<form action="MessageLoginCheck" method="post" class="form-signin">

		<img class="mb-4" src="img/iconmonstr-cat-3-240.png" alt=""
			width="100" height="100">
		<h1 class="h3 mb-3 font-weight-normal"><b>＜hidden＞</b>にログイン</h1>
		<%if(directAccess!=null){%>
		<p>ログインしてください</p>
		<% } %>
		<%if(status!=null){%>
		<p class="p-red">従業員コードまたはパスワードが<br>正しくありません</p>
	<% } %>
		<input type="text" name="employeeCode"
		pattern="E[0-9]{4}" size="8" maxlength="5" required
		class="form-control" placeholder="ID">

		<input type="password" name="mailPass" size="8" maxlength="5"
		 required class="form-control" id="inputPassword"  placeholder="password"><br><br>
		<input type="submit" value="Sign in" class="btn btn-lg btn-primary btn-block">
		<hr>
		管理者ログインは<a href="login.jsp">こちら</a>
	</form>


</body>
</html>
<%
session.removeAttribute("passErr");
session.removeAttribute("directAccess");
%>