<%@page import="com.tables.Order"%>
<%@page import="com.services.AdminService"%>
<%@ page language="java"%>
<html>
<head>
<title></title>
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
	<form action="CustomerFeedback" method="post">
		<div class="row">
			<div class="col-md-6">
				<label>Order Id</label> <input class="w3-input" type="text"
					name="name" id="name" value="<%=order.getId()%>" disabled>
			</div>
			<div class="col-md-6">
				<label>Date</label> <input class="w3-input" type="text" name="email"
					id="date" value="<%=order.getDate()%>" disabled>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<label>Feedback</label>
				<textarea style="float: left; width: 100%; resize: none;" rows="5"
					value="<%=order.getNote()%>">
						</textarea>
			</div>
		</div>
		<div class="row">
			<input type="submit" value="Submit">
		</div>
	</form>
</body>
</html>