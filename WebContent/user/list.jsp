<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Form User</title>
</head>
<body>
	<h4>User List</h4>
	
	<c:if test="${not empty user}">
		<h5>Usuario ${user.name} ${action} com sucesso!</h5>
	</c:if>
	
	<c:forEach var="listItem" items="${userList}">
		<p>
			${listItem.name}
			<a href="User.action?user.id=${listItem.id}">Edit</a>
			<a href="UserList.action?user.id=${listItem.id}">Delete</a>
		</p>
	</c:forEach>
</body>
</html>