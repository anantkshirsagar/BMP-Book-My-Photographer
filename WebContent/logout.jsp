<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body onload="logout()">
	<center>
		<h2 style="color: red">You are logout from system!!</h2>
	</center>
	<center>
		<a href=login.html>Click here to go to Login page.</a>
	</center>
	<script type="text/javascript">
		function logout() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
				}
			};
			xhr.open('POST', "LogoutServlet", true);
			xhr.send();
		}
	</script>
</body>
</html>