<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div align="center">
		<h1>Users List</h1>
		<h2>
			<a href="new">New User</a>
		</h2>

		<table border="1">
			<th>No</th>
			<th>UserName</th>
			<th>Email</th>
			<th>Actions</th>

			<c:forEach var="user" items="${userList}" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${user.username }</td>
					<td>${user.email }</td>
					<td><p><a href="edit?id=${user.id }">Edit</a></p>
						<p><a href="delete?id=${user.id }">Delete</a></p>
						<p><a href="test">Test</a></p>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>