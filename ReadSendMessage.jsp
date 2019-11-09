<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MessageBean,model.EmployeeBean,java.util.List"%>
<%
EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");

if(emp==null){
	session.setAttribute("directAccess", "directAccess");
	response.sendRedirect("MessageMenu.jsp");
}else{
List<MessageBean> sendList=(List<MessageBean>)session.getAttribute("sendList");
int id=Integer.parseInt(request.getParameter("id"));
session.setAttribute("sendBoxFlag", true);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>送信済みメッセージ</title>
</head>

<body class="block container-fluid">
<%@ include file="userheader.jsp"%>

<input type="button" onclick="location.href='MessageBox'" value="送信メッセージ一覧に戻る" class="btn">
<h1>送信済みメッセージ</h1>
送信先：<%=sendList.get(id).getFullName() %>　メッセージ受信日時：<%=sendList.get(id).getPostDatetime() %>
<form action="MessageDelete" class ="inline">
<input type="submit" value="削除" class="inline btn btn-danger">
<input type="hidden" name="sendMsg" value="<%=sendList.get(id).getMessageId() %>">
</form>
<br/>
<hr>
<%=sendList.get(id).getMessageBody() %><br/><br/><br/>

	<!-- ここの送信先、送信名、送信する値を要確認↓ -->


<br/>
</body>
</html>
<% } %>