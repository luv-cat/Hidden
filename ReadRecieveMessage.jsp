<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MessageBean,model.EmployeeBean,java.util.List"%>
<%
EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");

if(emp==null){
	session.setAttribute("directAccess", "directAccess");
	response.sendRedirect("MessageMenu.jsp");
}else{
List<MessageBean> recieveList=(List<MessageBean>)session.getAttribute("recieveList");
int id=Integer.parseInt(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>受信メッセージ</title>
</head>

<body  class="block container-fluid">
<%@ include file="userheader.jsp"%>
<input type="button" onclick="location.href='MessageBox'" value="受信ボックスに戻る" class ="btn">
<h1>受信メッセージ</h1>
送信者：<%=recieveList.get(id).getFullName() %>　メッセージ受信日時：<%=recieveList.get(id).getPostDatetime() %>
<form action="ReplyMsg"style="display: inline"method="Post">
	<!-- ここの送信先、送信名、送信する値を要確認↓ -->
<input type="hidden" name="msgId" value="<%=recieveList.get(id).getMessageId()%>">
<input type="hidden" name="msgBody" value="<%=recieveList.get(id).getMessageBody() %>">
<input type="submit" value="返信" class ="inline btn btn-default">
</form>

<form action="MessageDelete"style="display: inline">
	<!-- ここの送信先、送信名、送信する値を要確認↓ -->
<input type="hidden" name="recvMsg" value="<%=recieveList.get(id).getMessageId() %>">
<input type="submit" value="削除" class ="inline btn btn-danger">
</form>
<hr>
<%=recieveList.get(id).getMessageBody() %>



<br/>
</body>
</html>
<% } %>