<%@page import="com.tables.Photographer"%>
<%@page import="com.tables.Customer"%>
<%@page import="com.tables.Order"%>
<%@page import="com.bmp.utils.AppConstants.PhotographerStatus"%>
<%@page import="com.services.AdminService"%>
<%@page import="java.util.List"%>
<%@ page language="java"%>
<html>
<head>
<title>Approval List</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/photographer-nav-bar.html'"></div>
</head>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
			<div class="w3-responsive">
				<table class="w3-table-all">
					<thead>
						<tr class="w3-green">
							<th>Id</th>
							<th>Title</th>
							<th>Date</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							AdminService adminService = new AdminService();
							String email = (String) session.getAttribute("email");
							Photographer photographer = adminService.getPhotographerByEmailId(email);
							List<Order> orderList = adminService.getOrdersByPhotographerId(String.valueOf(photographer.getId()));
							for (Order order : orderList) {
						%>
						<tr>
							<td><%=order.getId()%></td>
							<td><%=order.getTitle()%></td>
							<td><%=order.getDate()%></td>
							<td><%=order.getStatus()%></td>
							<td><a
								href="photographer-view-order.jsp?id=<%=order.getId()%>">View
									Order</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>