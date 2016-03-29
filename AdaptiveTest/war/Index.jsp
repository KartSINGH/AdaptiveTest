<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Test</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet">
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="navbar-fixed">
		<nav class="indigo lighten-1" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo">MyMockTest</a>
				<ul class="right hide-on-med-and-down ">
					<li><a href="/">Home</a></li>
					<li><a href="/about">About Us</a></li>
					<%
						if (session.getAttribute("uID") == null) {
					%>
					<li><a href="/loginPage">Login</a></li>
					<%
						} else {
					%>
					<li><a class="dropdown-button" href="#!"
						data-activates="dropdown"><%=session.getAttribute("name")%><i
							class="material-icons right">arrow_drop_down</i></a></li>
					<%
						}
					%>
				</ul>
				<ul id="nav-mobile" class="side-nav">
					<li><a href="/">Home</a></li>
					<li><a href="/about">About Us</a></li>
					<%
						if (session.getAttribute("uID") == null) {
					%>
					<li><a href="/loginPage">Login</a></li>
					<%
						} else {
					%>
					<li><a class="dropdown-button" href="#!"
						data-activates="dropdownMobile"><%=session.getAttribute("name")%><i
							class="material-icons right">arrow_drop_down</i></a></li>
					<%
						}
					%>
				</ul>
				<ul id="dropdown" class="dropdown-content">
					<li><a href="/user">Dashboard</a></li>
					<li><a href="/setting">Settings</a></li>
					<li class="divider"></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
				<ul id="dropdownMobile" class="dropdown-content">
					<li><a href="/user">Dashboard</a></li>
					<li><a href="/setting">Settings</a></li>
					<li class="divider"></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i
					class="material-icons">menu</i></a>
			</div>
		</nav>
	</div>

	<div class="parallax-container">
		<div class="parallax">
			<img src="/images/bg.jpg">
		</div>
	</div>
	<div class="section white">
		<div class="row container center">
			<h1 class="flow-text text">
				Welcome to <span style="color: red">ONE STOP SOLUTION</span> for
				your placement preparations
			</h1>
			<h1 class="flow-text text">
				<span class="typewriter text"> While ( ! ( Succeed=try() ) )
					; </span>
			</h1>
			<h5 class="flow-text text">Register before You Can start</h5>
			<div class="register">
				<a href="/loginPage">
					<button class="btn waves-effect waves-light red">
						Register <i class="material-icons right">send</i>
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="parallax-container">
		<div class="parallax">
			<img src="/images/bg.jpg">
		</div>
	</div>

	<footer class="page-footer indigo darken-4">
		<div class="container">
			<div class="row">
				<div class="col s12">
					<h5 class="white-text">Company Bio</h5>
					<p class="grey-text text-lighten-4">We are a team of college
						students working on this project like it's our full time job. Any
						amount would help support and continue development on this project
						and is greatly appreciated.</p>


				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">Copyright</div>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>
	<script src="js/main.js"></script>
	<script src="js/typewriter.js"></script>
</body>
</html>