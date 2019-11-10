<%@page import="com.tables.Order"%>
<%@page import="com.services.AdminService"%>
<%@ page language="java"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html>
<head>
<title>Feedback</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/admin-nav-bar.html'"></div>
</head>
<body ng-app="">
	<%
		String orderId = request.getParameter("id");
		AdminService adminService = new AdminService();
		Order order = adminService.getOrderById(orderId);
	%>
	<br>
	<div class="container">
		<form action="CustomerFeedback" method="post">

			<input type="hidden" name="orderId" id="orderId" value="<%=order.getId()%>">
			<div class="row">
				<div class="col-md-6">
					<label>Order Id</label> <input class="w3-input" type="text"
						name="id" id="id" value="<%=order.getId()%>" disabled>
				</div>
				<div class="col-md-6">
					<label>Date</label> <input class="w3-input" type="text" name="date"
						id="date" value="<%=order.getDate()%>" disabled>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-12">
					<label>Feedback</label>
					<textarea style="float: left; width: 100%; resize: none;" rows="5"
						name="feedback" id="feedback">
						</textarea>
				</div>
			</div>
			<br>
			<div class="row">
				<input class="w3-button w3-black" type="submit"
					value="Submit Feedback">
			</div>
		</form>
	</div>
</body>
</html>