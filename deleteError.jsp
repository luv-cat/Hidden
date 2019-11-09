<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp" %>
<title>削除エラー</title>
</head>

<body class="block container-fluid">
<%@ include file="userheader.jsp"%>
<h1>メッセージの削除に失敗しました。</h1>
<form action="MessageBox">
<input type="submit" value="メッセージ一覧に戻る" class="btn btn-default">
</form>
<br><br>

</body>
</html>