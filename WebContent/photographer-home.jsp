<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.html");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Photographer Home</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/photographer-nav-bar.html'"></div>
</head>
<body ng-app="">
</body>
</html>