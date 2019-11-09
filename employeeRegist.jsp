<%--
　　@author <hidden>
 --%>

<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.User, dao.FunctionMtd"
%>
<%
	String rand = FunctionMtd.randomchar();
	User user = (User) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>社員情報登録</title>
</head>
<body class="block container-fluid">
<%@ include file="header.jsp"%>
<div class ="block">
<input type="button" value="メニューへ戻る" onclick="location.href='menu.jsp'" class ="btn">
<h1>社員情報登録</h1>
<hr>
<form action="RegistCheck" method="post">
<ul class ="input">


		<li class="name">
            <label class ="labellist">社員コード：</label>
            <input type="text" name="employeeCode" size="8" maxlength="5" pattern="E[0-9]{4}" required placeholder="E0000">
        </li>

		<li class="name">
            <label class ="labellist">氏（漢字）：</label>
           <input type="text" name="lastName" size="16" maxlength="16" pattern="\S+" pattern="\D" required>

            <label class ="labellist">名（漢字）：</label>
            <input type="text" name="firstName" size="16" maxlength="16" pattern="\S+" pattern="\D" required>
       	</li>

        <li class="name">
            <label class ="labellist">氏（かな）：</label>
            <input type="text" name="lastKanaName" size="16" maxlength="16" pattern="[ぁ-ん]\S+"required>

            <label class ="labellist">名（かな）：</label>
            <input type="text" name="firstKanaName" size="16" maxlength="16" pattern="[ぁ-ん]\S+"required>
        </li>

		<li class="name">
			<label class ="labellist">メールパス：<%= rand %></label>
			<input type="hidden" name="mailPass" value="<%= rand %>">
		</li>

        <li class="name">
            <label class ="labellist">性別：</label>
            <input type="radio" name="gender" value="1" checked>男性
			<input type="radio" name="gender" value="2">女性
        </li>


<li class="name">
            <label class ="labellist">生年月日：</label>
            <input type="date" name="birthDay" min="1950-01-01" max="2050-12-31" required>
        </li>


<li class="name">
            <label class ="labellist">所属部署：</label>
            <select name="sectionCode">
<option value=S000>所属部署未定</option>
<option value=S110>総務部</option>
<option value=S130>人事部</option>
<option value=S150>経理部</option>
<option value=S210>企画部</option>
<option value=S230>営業部</option>
</select>
        </li>

<li class="name">
            <label class ="labellist">入社日    ：</label>
<input type="date" name="hireDate" min="1980-01-01" max="2050-12-31" required>
        </li>

        <li class="name">
<input type="submit" value="登録" class ="btn btn-primary right">
        </li>




</ul>
</form>
</div>

</body>
</html>
<% } %>