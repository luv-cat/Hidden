<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.EmployeeBean, model.User"
%>

<%!
//String[] section={"所属部署未定","総務部","人事部","経理部","企画部","営業部"};
String GenderM="",GenderF="",sectionA="",sectionB="",sectionC="",sectionD="",sectionE="",sectionF="";
String sectionName="";
%>
<%
//データをセッションから取得
EmployeeBean e=(EmployeeBean)session.getAttribute("editData");
User user =(User)session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
} else {
//性別のラジオボタンにcheckedを挿入
switch(e.getGender()){
	case 1:
		GenderM="checked";
		break;
	case 2:
		GenderF="checked";
		break;
}
//所属部署のプルダウンにselectedを挿入、セクションコードを部署名に変更する
switch(e.getSectionCode()){
	case "S000":
		sectionA="selected";
		sectionName="所属部署未定";
		break;
	case "S110":
		sectionB="selected";
		sectionName="総務部";
		break;
	case "S130":
		sectionC="selected";
		sectionName="人事部";
		break;
	case "S150":
		sectionD="selected";
		sectionName="経理部";
		break;
	case "S210":
		sectionE="selected";
		sectionName="企画部";
		break;
	case "S230":
		sectionF="selected";
		sectionName="営業部";
		break;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>従業員情報編集</title>
</head>
<body class="block container-fluid">
<%@ include file="header.jsp"%>
<div class ="block">
<input type="button" value="従業員情報一覧へ戻る" onclick="history.back()" class="btn">
<h1>従業員情報編集</h1>
<hr>

<form action="EditCheck" method="get">
<ul class ="input">
<li class="name">
            <label class ="labellist">従業員コード：</label>
	<%= e.getEmployeeCode()%>
	</li>

	<li class="name">
            <label class ="labellist">氏（漢字）：</label>
			<input type="text" name="lastName" value="<%=e.getLastName() %>" size="16" maxlength="16" required>
           	<label class ="labellist">名（漢字）：</label>
			<input type="text" name="firstName" value="<%=e.getFirstName() %>" size="16" maxlength="16" required>
	</li>

	<li class="name">
        <label class ="labellist">氏（かな）：</label>
		<input type="text" name="lastKanaName" value="<%=e.getLastKanaName() %>" size="16" maxlength="16" required>
		<label class ="labellist">名（かな）：</label>
		<input type="text" name="firstKanaName" value="<%=e.getFirstKanaName() %>" size="16" maxlength="16" required>
	</li>

	<li class="name">
	<label class ="labellist">メールパス：</label>
	<input type="text" name="mailPass" value="<%= e.getMailPass() %>" size="5" maxlength="5" pattern="[0-9a-zA-Z]{5}">
	</li>

	<li class="name">
        <label class ="labellist">性別：</label>
	<input type="radio" name="gender" value="1" <%=GenderM %>>男
	<input type="radio" name="gender" value="2" <%=GenderF %>>女
	</li>

	<li class="name">
        <label class ="labellist">生年月日：</label>
	<input type="date" name="birthDay" value="<%=e.getBirthDay() %>"  min="1950-01-01" max="2050-12-31" required>
	</li>

	<li class="name">
        <label class ="labellist">所属部署：</label>
	<select name="sectionCode" >
		<option value="S000" <%=sectionA %>>所属部署未定</option>
		<option value="S110" <%=sectionB %>>総務部</option>
		<option value="S130" <%=sectionC %>>人事部</option>
		<option value="S150" <%=sectionD %>>経理部</option>
		<option value="S210" <%=sectionE %>>企画部</option>
		<option value="S230" <%=sectionF %>>営業部</option>
	</select>
	</li>

	<li class="name">
        <label class ="labellist">入社日</label>
	<input type="date" name="hireDate" value="<%=e.getHireDate() %>" min="1980-01-01" max="2050-12-31" required>
	</li>

	<li class="name">
	<input type="hidden" name="employeeCode" value="<%=e.getEmployeeCode() %>">
	<input type="submit" value="編集内容の確定" class ="btn btn-primary right">
	</li>

	</ul>
</form>
</div>
</body>
</html>
<% } %>