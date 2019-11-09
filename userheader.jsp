<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
	<!-- トップ固定ナビゲーション -->


	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<!-- Topページに戻る -->
		<a class="navbar-brand" href="MessageMenu.jsp">
		<img src="img/iconmonstr-cat-3-240w.png" alt="" width="30" height="30">
			hidden
		</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#Navbar" aria-controls="Navbar" aria-expanded="false"
			aria-label="ナビゲーションの切替">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
				<button class="btn btn-outline-success my-2 my-sm-0"
				 onClick="location.href='SenderList'">メッセージ作成</button>
				</li>
			</ul>
		</div>

		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
				<a class="nav-link" href="MessageMenu.jsp">受信ボックス</a></li>
			</ul>
		</div>

		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
				<a class="nav-link" href="SendMessage.jsp">送信済みボックス</a></li>
			</ul>
		</div>
		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
				<a class="nav-link" href="MessageLogout.jsp">ログアウト</a></li>
			</ul>
		</div>
	</nav>
</header>