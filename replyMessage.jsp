<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String fullName = (String)session.getAttribute("fullName"); %>
<%String returnId = (String)session.getAttribute("returnId"); %>
<%String msgBody = (String)session.getAttribute("msgBody"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>返信メッセージ作成</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>

<input type="button" onclick="location.href='MessageMenu.jsp'" value="受信ボックスへ戻る" class ="btn">
<h1>返信メッセージ作成</h1>

<div class ="block container-fluid">
<hr>

<form action="MessageCheck" method="Post">

<ul class ="input">
<li class="name">
<label class ="labellist">返信先：</label>
<%=fullName%>
</li>

<li class="name">
<label class ="labellist">本文  ：</label>
<textarea name="msgBody" rows="4" cols="40">><%=msgBody %></textarea>
</li>

<li class="name">
<input type="hidden" name="returnId" value="<%=returnId %>">
<input type="submit"value="送信する" class="btn btn-success">
</li>

</ul>
</form>
</div>
</body>
</html>
<%session.removeAttribute("msgBody");%>
<%session.removeAttribute("fullName");%>
<%session.removeAttribute("returnId");%>