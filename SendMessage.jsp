<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MessageBean,model.EmployeeBean,java.util.List"%>
<%
EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");

if(emp==null){
	session.setAttribute("directAccess", "directAccess");
	response.sendRedirect("MessageMenu.jsp");
}else{
List<MessageBean> sendList=(List<MessageBean>)session.getAttribute("sendList");
List<MessageBean> sendReplaceList=(List<MessageBean>)session.getAttribute("sendReplaceList");
%>
<%int i =1; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>送信済みボックス</title>
</head>

<body class="block container-fluid">
<%@ include file="userheader.jsp"%>

<div class="block">
<input type="button" onclick="location.href='MessageBox'" value="受信ボックスへ戻る" class ="btn">
<h1>送信済みボックス</h1><br/>
</div>


<div class = "block listbox">


<table class="table table-hover">
<thead>
<tr>

<th>No.</th>
<th>送信日時</th>
<th>メッセージ</th>
<th>送信者氏名</th>
<th>削除ボタン</th>
</tr>
</thead>

<tbody>
<%for(MessageBean mb:sendReplaceList){%>
<tr>
<td><%=i++%></td>
<td><%=mb.getPostDatetime() %></td>

<td><a href="ReadSendMessage.jsp?id=<%=sendReplaceList.indexOf(mb) %>">
		<script>
			var str="<%=mb.getMessageBody() %>";
			var str20=str.slice(0,20);
			document.write(str20);
		</script></a>
</td>

<td><%=mb.getFullName() %></td>

<td><form action="MessageDelete" class="inline">
	<!-- ここの送信先、送信名、送信する値を要確認↓ -->
<input type="hidden" name="sendMsg" value="<%=mb.getMessageId() %>">
<input type="submit" value="削除" class="btn btn-danger">
</form></td>

</tr>
</tbody>




<% } %>

</table>
</div>
</body>
</html>
<% } %>
<%session.removeAttribute("sendBoxFlag");%>