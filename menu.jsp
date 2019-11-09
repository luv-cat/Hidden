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
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<%@ include file="boot.jsp"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>メニュー画面</title>
</head>

<body class="pt-5 block">
	<%@ include file="header.jsp"%>

	<main role="main">
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h3>従業員管理システム</h3>
			<p>メニューを選択してください</p>
		</div>
	</div>


	<div class ="row b-center">
		<div>
			<form action="employeeRegist.jsp" class="inline">
				<input type="submit" value="社員登録" class="btn-circle-flat">
			</form>
			<br> <br>
		</div>
		<br>
		<div>
			<form action="ShowAllList" class="inline">
				<input type="submit" value="従業員一覧" class="btn-circle-flat">
			</form>
			<br> <br>
		</div>
		<br>
		<div>
			<form action="logout.jsp" class="inline">
				<input type="submit" value="ログアウト" class="btn-circle-flat">
			</form>
		</div>
		<br>



	</div>

	</main>

</body>
</html>
<% } %>