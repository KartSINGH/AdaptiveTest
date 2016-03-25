<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Question</title>
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body>
	<div class="container">
		<form method="post" action="/addQuestion">
			<input type="text" name="question" required placeholder="Question">
			<input type="text" name="answer" required placeholder="Answer">
			<input type="text" name="option0" required placeholder="Option 1">
			<input type="text" name="option1" required placeholder="Option 2">
			<input type="text" name="option2" required placeholder="Option 3">
			<input type="text" name="course" placeholder="Course"> <input
				type="number" name="difficulty" required placeholder="Difficulty">
			<input type="submit">
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>

</body>
</html>