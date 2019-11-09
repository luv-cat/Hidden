<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>エラー</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>
<h2>退職済み社員のため、メッセージの送信はできません。</h2>

<form action="MessageBox">
<input type=submit value="受信ボックスに戻る" class="btn btn-default" >
</form>

</body>
</html>