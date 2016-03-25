<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/addQuestion">
		<input type="text" name="question" required> <input
			type="text" name="answer" required> <input type="text"
			name="option0" required> <input type="text" name="option1"
			required> <input type="text" name="option2" required>
		<input type="text" name="course"> <input type="submit">
	</form>

</body>
</html>