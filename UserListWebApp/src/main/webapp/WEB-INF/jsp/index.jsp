<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>User List</title>
<script>
		function deleteRow(userId) {
			$.ajax({
                type: "GET",
                url: "deleteUser",
                data: {userId: userId},
                success: function (message) {
                	location.reload(true);
                },
            });
		}
		
		function assignRowToEdit(id, name, email, mobile) {
			document.getElementById("id").value = id;
			document.getElementById("name").value = name;
			document.getElementById("email").value = email;
			document.getElementById("mobile").value = mobile;
		}

	</script>
</head>
<body>
	<form id="addEditUserForm" action="addEditUser">
		<input type="hidden" id="id" name="id">
		<p>
			Name: <input id="name" name="name" type="text" />
		</p>
		<p>
			Email: <input id="email" name="email" type="text" />
		</p>
		<p>
			Mobile: <input id="mobile" name="mobile" type="text" />
		</p>
		<button type="submit" value="Submit">Submit</button>
	</form>
	<table border="1" id="userListTable" onsubmit="setTimeout(function(){window.location.reload();},10);">
		<tbody>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Mobile</td>
				<td>Update</td>
			</tr>
			<c:forEach var="user" items="${list}">
				<tr>
					<td>${user.NAME}</td>
					<td>${user.EMAIL}</td>
					<td>${user.MOBILE}</td>
					<td><a href="#" onclick="assignRowToEdit(${user.ID}, '${user.NAME}', '${user.EMAIL}', '${user.MOBILE}')">[Edit]</a>
						<a href="#" onclick="deleteRow(${user.ID})">[Delete]</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		document.getElementById("addEditUserForm").onsubmit = function(){
		    location.reload(true);
		}
	</script>
</body>
</html>