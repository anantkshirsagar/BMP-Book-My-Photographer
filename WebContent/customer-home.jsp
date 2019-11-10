<%@page import="java.time.LocalDate"%>
<%@page import="com.tables.Photographer"%>
<%@page import="com.tables.Order"%>
<%@page import="com.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Home</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
</head>
<%
	AdminService adminService = new AdminService();
	Order todaysOrder = adminService.isAnyOrderFindByDate(LocalDate.now());
	Photographer photographer = null;
%>
<body ng-app="">
	<div class="container">
		<br>
		<h3>
			<center>
				<b>Customer Home</b>
			</center>
		</h3>
		<br>
		<div class="row">
			<div class="col-md-12">
				<div class="w3-card-4 w3-dark-grey"
					style="width: 100%; object-fit: cover;">
					<div class="w3-container w3-center">
						<br>
						<h3>
							Todays Order -
							<%=LocalDate.now()%></h3>
						<br>
						<%
							if (todaysOrder != null) {
								photographer = adminService.getPhotographerById(String.valueOf(todaysOrder.getPhotographerId()));
						%>
						<div>
							Photographer Name -
							<%=photographer.getName()%>
							<br> Order No -
							<%=todaysOrder.getId()%>
							<br> Order Time -
							<%=todaysOrder.getTime()%>
							<br> <br> <a class="w3-button w3-black"
								href="customer-view-order.jsp?id=<%=todaysOrder.getId()%>">View
								Order</a><br> <br>
						</div>
						<%
							} else {
						%>
						<h4>There is no order Today.</h4>
						<br> <br>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>