<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List,servlet.ShowAllList,model.EmployeeBean, model.User"%>
<%
	List<EmployeeBean> empList = (List<EmployeeBean>) session.getAttribute("empList");
	User user = (User) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {
		String sectionName = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="boot.jsp"%>
<title>従業員一覧</title>
</head>
<body class="block container-fluid">
	<%@ include file="header.jsp"%>
	<input type="button" value="メニューに戻る"
			onclick="location.href='menu.jsp'" class="btn">
		<h1>従業員一覧</h1>
		<input type="button" value="従業員新規登録"
			onclick="location.href='employeeRegist.jsp'" class="btn btn-primary right">



		<div class="listbox b-center">
			<table class="table table-striped table-striped">
				<thead>
					<tr>

						<th>従業員コード</th>
						<th>氏</th>
						<th>名</th>
						<th>氏 かな</th>
						<th>名 かな</th>
						<th>性別</th>
						<th>生年月日</th>
						<th>所属部署名</th>
						<th>入社日</th>
						<th>編集</th>
						<th>削除</th>
					</tr>
				</thead>

				<tbody>
					<%
						for (EmployeeBean eList : empList) {
					%>
					<tr>
						<td><%=eList.getEmployeeCode()%></td>
						<td><%=eList.getLastName()%></td>
						<td><%=eList.getFirstName()%></td>
						<td><%=eList.getLastKanaName()%></td>
						<td><%=eList.getFirstKanaName()%></td>
						<%
							String gender = null;
						%>
						<%
							if (eList.getGender() == 0) {
						%>
						<%
							gender = "不明";
									} else if (eList.getGender() == 1) {
						%>
						<%
							gender = "男";
									} else if (eList.getGender() == 2) {
						%>
						<%
							gender = "女";
									} else {
										gender = "適用不能";
									}
						%>
						<td><%=gender%></td>
						<td><%=eList.getBirthDay()%></td>
						<%
							switch (eList.getSectionCode()) {
									case "S000":
										sectionName = "所属部署未定";
										break;
									case "S110":
										sectionName = "総務部";
										break;
									case "S130":
										sectionName = "人事部";
										break;
									case "S150":
										sectionName = "経理部";
										break;
									case "S210":
										sectionName = "企画部";
										break;
									case "S230":
										sectionName = "営業部";
										break;
									}
						%>
						<td><%=sectionName%></td>
						<td><%=eList.getHireDate()%></td>

						<td><form action="GetEmployeeData" class="inline">
								<input type="hidden" name="employeeCode"
									value="<%=eList.getEmployeeCode()%>"> <input
									type="submit" value="編集" class="btn btn-default">
									<input type="hidden" name="judge"value="0">
							</form></td>

						<td>
							<form action="GetEmployeeData" class="inline">
								<input type="hidden" name="employeeCode"
									value="<%=eList.getEmployeeCode()%>"> <input
									type="submit" value="削除" class="btn btn-danger">
									<input type="hidden" name="judge"value="1">
							</form>
						</td>

					</tr>
					<%
						}
					%>

				</tbody>
			</table>
		</div>


</body>
</html>
<%
	}
%>