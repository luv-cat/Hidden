<%-- 樋口 --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="boot.jsp" %>
<title>管理者用ログイン</title>
</head>
<body class="text-center black">
	<form action="LoginCheck" method="post" class="form-signin">
	<img class="mb-4" src="img/iconmonstr-cat-3-240w.png" alt="" width="100" height="100">
	<h1 class="h3 mb-3 font-weight-normal p-white">管理者用ログイン</h1>


<input type="text" name="userId" class="form-control" id="inputEmail"  placeholder="ID">
<input type="password" name="password" class="form-control" id="inputPassword" placeholder="password"><br><br>


<input type="submit" value="Sign In" class="btn btn-lg btn-primary btn-block">
<hr>
<a href = "MessageMenu.jsp">＜hidden＞</a>

	</form>
</body>
</html>