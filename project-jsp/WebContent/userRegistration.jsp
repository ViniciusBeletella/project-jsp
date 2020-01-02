<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<link rel="stylesheet" href="resources/css/registration.css">
</head>
<body>

	<center>
		<h1>User Registration</h1>
	</center>
	</tr>
	<tr></tr>
	<tr>
	<center>
	<h3 style="color:orange;">${msg}</h3>
			<form action="saveUser" method="post" id="formUser">
				<ul class="form-style-1">
					<li>
						<table>
							<tr>
								<td>Codigo:</td>
								<td><input type="text" readonly="readonly" id="id"
									name="id" value="${user.id}"></td>
							</tr>
							<tr>
								<td>Login:</td>
								<td><input type="text" id="login" name="login"
									value="${user.login}"></td>
							</tr>

							<tr>
								<td>Password:</td>
								<td><input type="password" id="password" name="password"
									value="${user.password}"></td>
							</tr>
							<tr>
								<td>Name:</td>
								<td><input type="text" id="name" name="name"
									value="${user.name}"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Save"><input type="submit" value="Cancel" onclick="document.getElementById('formUser').action = 'saveUser?acao=reset'"></td>
							</tr>
						</table>
					</li>
				</ul>
			</form>

		<table>
				<caption>Users Registred</caption>
				<tr bgcolor="#3498DB">
				<th>Cod ID:</th>
				<th>Login User</th>
				<th>Name</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
			<c:forEach items="${usuarios}" var="userL">
				<tr>
					<td style="width: 150px"><c:out value="${userL.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${userL.login}"></c:out></td>
					<td style="width: 150px"><c:out value="${userL.name}"></c:out></td>
					<td><c:out value="${userL.password}"></c:out></td>
					<td><a href="saveUser?acao=delete&user=${userL.id}"><img src="resources/img/delete.png" title="Delete" width="18px" height="18px"></a>
					</td>
					<td><a href="saveUser?acao=edit&user=${userL.id}"><img src="resources/img/edit.png" title="Edit" width="18px" height="18px"></a></td>

				</tr>

			</c:forEach>
		</table>
		</center>
</body>
</html>