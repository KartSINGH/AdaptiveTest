<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Login</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/login.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<nav class="indigo lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="#" class="brand-logo">MyMockTest</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="/">Home</a></li>
				<li><a href="/loginPage">Login</a></li>
			</ul>
			<ul id="nav-mobile" class="side-nav">
				<li><a href="/">Home</a></li>
				<li><a href="/loginPage">Login</a></li>
			</ul>
			<a href="#" data-activates="nav-mobile" class="button-collapse"><i
				class="material-icons">menu</i></a>
		</div>
	</nav>

	<div class="row mainPage">
		<div class="col s6 m9 l9 left home">
			<div class="slider">
				<ul class="slides">
					<li><img src="images/banner1.jpg">
						<div class="caption center-align">
							<h5 class="light grey-text text-lighten-3">Here's our small
								slogan.</h5>
						</div></li>
					<li><img src="images/banner2.jpg">
						<div class="caption left-align">
							<h3>Left Aligned Caption</h3>
							<h5 class="light grey-text text-lighten-3">Here's our small
								slogan.</h5>
						</div></li>
					<li><img src="images/banner4.jpg">
						<div class="caption right-align">
							<h3>Right Aligned Caption</h3>
							<h5 class="light grey-text text-lighten-3">Here's our small
								slogan.</h5>
						</div></li>

				</ul>
			</div>

		</div>
		<div class="col s6 m3 l3 right">
			<div class="row">
				<form method="post" action="/login">
					<div>
						<h1 align="center" class="flow-text">Login</h1>
						<div class="input-field col s12">
							<i class="material-icons prefix">account_circle</i> <input
								id="icon_prefix" type="email" name="email" class="validate"
								required> <label for="icon_prefix">Login ID</label>
						</div>
						<div class="input-field col s12">
							<i class="material-icons prefix">polymer</i> <input
								id="icon_telephone" type="password" name="pass" class="validate"
								required> <label for="icon_telephone">Password</label>
						</div>

						<div class="row center">

							<button class="btn waves-effect waves-light" type="submit"
								name="action">
								Submit <i class="material-icons right">send</i>
							</button>
						</div>
						<p class="flow-text center">
							If you are a new user <a href="/registerPage">Register</a>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<footer class="page-footer indigo darken-4">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
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
	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>
	<script src="js/login.js"></script>
</body>
</html>
