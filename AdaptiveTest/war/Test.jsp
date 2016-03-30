<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<%
	if (session.getAttribute("uID") == null) {
		response.sendRedirect("/loginPage");
	}
%>

<title>Test</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/login.css" type="text/css" rel="stylesheet" />
<link href="css/user.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="navbar-fixed">
		<nav class="indigo lighten-1" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo">MyMockTest</a>
			</div>
		</nav>
	</div>

	<div class="container mainPage z-depth-2">
		<div class="row">
			<div class="col s10 offset-s1">
				<div class="headingPad">
					<span class="right flow-test" id="timer"></span>
					<h1 align="center" class="flow-text">Test</h1>
					<span class="right" id="timer"></span>

					<form onSubmit="getQuestion()">
						<label class="flow-text">Question: <span id="question"></span></label>
						<p>
							<input class="with-gap" name="group1" type="radio" id="test1" />
							<label for="test1" id="op1"></label>
						</p>
						<p>
							<input class="with-gap" name="group1" type="radio" id="test2" />
							<label for="test2" id="op2"></label>
						</p>
						<p>
							<input class="with-gap" name="group1" type="radio" id="test3" />
							<label for="test3" id="op3"></label>
						</p>
						<p>
							<input class="with-gap" name="group1" type="radio" id="test4" />
							<label for="test4" id="op4"></label>
						</p>
						<div class="row center">
							<button class="btn waves-effect waves-light" type="submit"
								name="action">
								Submit <i class="material-icons right">send</i>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>
	<script src="js/test.js"></script>
</body>
</html>