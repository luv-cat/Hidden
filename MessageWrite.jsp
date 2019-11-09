<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.MessageBean,model.EmployeeBean" %>
<% List<MessageBean>sdList = (List<MessageBean>)session.getAttribute("senderList");%>
<% EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>メッセージ作成</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>
<input type="button" onclick="location.href='MessageMenu.jsp'" value="受信ボックスへ戻る" class ="btn">
<h1>メッセージ作成</h1>
<div class ="block container-fluid">


<hr>

<form action="MessageCheck" method="Post">
<ul class ="input">
<li class="name">
<label class ="labellist">送信先：</label>
<select name="returnId">
<%for(MessageBean list : sdList){ %>
<option value="<%=list.getComEmpCode()%>">
<%=list.getFullName()%>
</option>
<%} %>
</select>
</li>

<li class="name">
<label class ="labellist">本文  ：</label>
<textarea name="msgBody" rows="4" cols="40"></textarea>
</li>

<li class="name">
<input type="submit"value="送信する" class="btn btn-success">
</li>

</ul>
</form>
</div>
</body>
</html>