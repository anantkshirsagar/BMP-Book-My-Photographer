<%@page import="com.tables.Order"%>
<%@page import="java.util.Base64"%>
<%@page import="com.model.FileContent"%>
<%@page import="com.services.AdminService"%>
<%@page import="com.tables.Photographer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
<script src="js/commons.js"></script>
<title>Customer View Order</title>
</head>
<%
	String id = request.getParameter("id");
	Order order = new AdminService().getOrderById(id);
	System.out.println(order.getNote());
%>
<body ng-app="">
	<div class="container">
		<br>
		<h3>
			<b>Order Details</b>
		</h3>
		<br>
		<div class="row">
			<div class="col-md-6">
				<label>Title</label> <input class="w3-input" type="text"
					name="title" id="title"
					value="<%=order.getTitle() != null ? order.getTitle() : ""%>"
					disabled>
			</div>
			<div class="col-md-2">
				<label>Status</label> <input class="w3-input" type="text"
					name="status" id="status"
					value="<%=order.getStatus() != null ? order.getStatus() : ""%>"
					disabled>
			</div>
			<div class="col-md-2">
				<label>Order Date</label> <input type="date" name="date" id="title"
					value="<%=order.getDate() != null ? order.getDate() : ""%>"
					disabled>
			</div>
			<div class="col-md-2">
				<label>Order Time </label><input type="time" name="time" id="title"
					value="<%=order.getTime() != null ? order.getTime() : ""%>"
					disabled>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<label>Address</label> <input class="w3-input" type="text"
					name="address" id="address"
					value="<%=order.getAddress() != null ? order.getAddress() : ""%>"
					disabled>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<label>Notes</label>
				<textarea style="float: left; width: 100%; resize: none;" rows="5"
					disabled>
					<%
						out.print(order.getNote() != null ? order.getNote() : "");
					%>
						</textarea>
			</div>
		</div>
		<br> <br>
		<!-- <button type="submit" class="w3-button w3-blue w3-large">Register</button>
					<button type="reset" class="w3-button w3-black w3-large">Clear</button> -->
		<br> <br> <br>
	</div>
</body>
</html>