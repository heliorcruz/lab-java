<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Form User</title>
</head>
<body>
	<h4>User Info</h4>
	<form action="UserForm.action" method="post">
		<input type="hidden" name="user.id" value="${req_user.id}">
		<p>Name: <input type="text" name="user.name" value="${req_user.name}"></p>
		<p>Lastname: <input type="text" name="user.lastName" value="${req_user.lastName}"></p>
		<p>Email: <input type="text" name="user.email" value="${req_user.email}"></p>
		<p>Password: <input type="password" name="user.password" value="${req_user.password}"></p> 		
		<input type="submit" value="Save">
	</form>
</body>
</html>