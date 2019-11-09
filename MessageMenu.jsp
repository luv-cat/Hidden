<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MessageBean,model.EmployeeBean,java.util.List"%>
<%
EmployeeBean emp=(EmployeeBean)session.getAttribute("empInstance");
if(emp==null){
	session.setAttribute("directAccess", "directAccess");
	response.sendRedirect("MessageLogin.jsp");
}else{
List<MessageBean> sendList=(List<MessageBean>)session.getAttribute("sendList");
List<MessageBean> recieveList=(List<MessageBean>)session.getAttribute("recieveList");
List<MessageBean> recieveReplaceList=(List<MessageBean>)session.getAttribute("recieveReplaceList");
%>
<%int i=1; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>マイページ</title>
</head>
<body class="block container-fluid">
<%@ include file="userheader.jsp"%>

<div class ="block">

<h5 class ="inline">ようこそ <%=emp.getLastName() %><%=emp.getFirstName() %>さん</h5>
<input type="button" onclick="location.href='MessageLogout.jsp'" value="SignOut" class="btn">


<h1>受信ボックス</h1>

<form action="SenderList" class="inline">
	<!-- ここの送信先、送信名、送信する値を要確認↓ -->
<input type="hidden" name="employeeCode" value="<%=emp.getEmployeeCode()%>">
<input type="submit" value="メッセージ作成" class="btn btn-success right">
</form>
<br/><br/>

</div>

<div class = " listbox block">
<table class="table table-hover">

<thead>
        <tr>
	        <th class="col-xs-2">No.</th>
            <th class="col-xs-2">受信日時</th>
            <th class="col-xs-4">メッセージ</th>
            <th class="col-xs-2">送信者氏名</th>
            <th class="col-xs-1">返信</th>
            <th class="col-xs-1">削除</th>
        </tr>
        </thead>

    <tbody>
    <%for(MessageBean mb:recieveReplaceList){%>
        <tr>
            <th><%= i++%></th>
            <td><%=mb.getPostDatetime() %></td>
            <td><a href="ReadRecieveMessage.jsp?id=<%=recieveReplaceList.indexOf(mb) %>"><script>
			var str="<%=mb.getMessageBody()%>";
			var str20=str.slice(0,20);
			document.write(str20);
		</script></a>
            </td>

            <td><%=mb.getFullName()%></td>


            <td><form action="ReplyMsg"method="Post"style="display: inline">
            <input type="hidden" name="msgId" value="<%=mb.getMessageId()%>">
			<input type="hidden" name="msgBody" value="<%=mb.getMessageBody()%>">
			<input type="submit" value="返信" class="btn btn-default">
			</form><br/>
            </td>

            <td><form action="MessageDelete"style="display: inline">
            <input type="hidden" name="recvMsg" value="<%=mb.getMessageId() %>">
            <input type="submit" value="削除" class="btn btn-danger">
            </form></td>


        </tr>

        <% } %>

    </tbody>

</table>
</div>


</body>
</html>
<% }%>
<%session.removeAttribute("sendBoxFlag");%>