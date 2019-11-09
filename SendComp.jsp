<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>送信完了</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>
<h2>メッセージを1件送信しました。</h2>
<br><br>
<form action="MessageWrite.jsp" class ="inline">
<input type="submit" value="メール作成画面に戻る"  class ="btn btn-default">
</form>

<form action="MessageBox" class ="inline">
<input type="submit" value="受信メッセージ一覧に戻る" class ="btn btn-success">
</form><br/><br/>
</body>
</html>