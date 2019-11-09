<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>送信失敗</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>
<h2>メッセージの送信に失敗しました</h2>

<form action="MessageWrite.jsp">
<input type="submit" value="メール作成画面に戻る" class="btn btn-default inline">
</form>

<form action="MessageBox">
<input type="submit" value="メッセージ一覧に戻る" class ="btn btn-success inline">
</form>


</body>
</html>