<%@page import="com.tables.Feedback"%>
<%@page import="com.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.html");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Approval List</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
</head>
<body ng-app="">
	<%
		String feedbackId = request.getParameter("id");
		AdminService adminService = new AdminService();
		Feedback feedback = adminService.getFeedbackById(feedbackId);
	%>
	<br>
	<div class="container">
		<form>
			<div class="row">
				<div class="col-md-6">
					<label>Id</label> <input class="w3-input" type="text" name="id"
						id="id" value="<%=feedback.getId()%>" disabled>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-12">
					<label>Feedback</label>
					<textarea style="float: left; width: 100%; resize: none;" rows="5"
						name="feedback" id="feedback" value="<%=feedback.getFeedback()%>"
						disabled>
						</textarea>
				</div>
			</div>
			<br>
		</form>
	</div>
</body>
</html>