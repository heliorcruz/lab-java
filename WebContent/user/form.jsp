<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Form User</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div align="center">
	<h4>User</h4>
	<form action="UserForm.action" method="get">
		<input type="hidden" name="user.id" value="${req_user.id}">
		<p><input class="textbox" type="text" name="user.name" value="${req_user.name}" placeholder="Name"></p>
		<p><input class="textbox" type="text" name="user.lastName" value="${req_user.lastName}" placeholder="Lastname"></p>
		<p><input class="textbox" type="text" name="user.email" value="${req_user.email}" placeholder="Email"></p>
		<p><input class="textbox" type="password" name="user.password" value="${req_user.password}" placeholder="Password"></p> 		
		<p>&nbsp&nbsp&nbsp&nbsp<input class="b" type="submit" value="Save">
	</form>
	</div>
</body>
</html>