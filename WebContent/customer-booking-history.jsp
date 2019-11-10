<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="org.apache.commons.collections4.CollectionUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.bmp.utils.AppConstants.OrderStatus"%>
<%@page import="com.tables.Customer"%>
<%@page import="com.tables.Order"%>
<%@page import="com.bmp.utils.AppConstants.PhotographerStatus"%>
<%@page import="com.services.AdminService"%>
<%@page import="java.util.List"%>
<%@ page language="java"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html>
<head>
<title>Book Order</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
</head>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
			<table class="w3-table-all">
				<thead>
					<tr class="w3-green">
						<th style="width: 10%">Id</th>
						<th style="width: 30%">Title</th>
						<th style="width: 20%">Date</th>
						<th style="width: 20%">Status</th>
						<th style="width: 10%">Actions</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<tbody>
					<%
						AdminService adminService = new AdminService();
						String email = (String) session.getAttribute("email");
						Customer customer = adminService.getCustomerByEmailId(email);
						List<Order> orderList = adminService.getOrdersByCustomerId(String.valueOf(customer.getId()));
						Date todaysDate = new Date();
						if (CollectionUtils.isNotEmpty(orderList)) {
							for (Order order : orderList) {
								System.out.println(order.getStatus());
								if (order.getDate().before(todaysDate) && !(order.getStatus().equals(OrderStatus.COMPLETED.name())
										|| order.getStatus().equals(OrderStatus.CANCELED.name()))) {
									if (order.getStatus().equals(OrderStatus.APPROVED.name())) {
										order.setStatus(OrderStatus.COMPLETED.name());
									} else if (!order.getStatus().equals(OrderStatus.REJECTED.name())) {
										order.setStatus(OrderStatus.CANCELED.name());
									}
									adminService.updateOrder(order);
								}
					%>
					<tr>
						<td><%=order.getId()%></td>
						<td><%=order.getTitle()%></td>
						<td><%=order.getDate()%></td>
						<td><%=order.getStatus()%></td>
						<td><a class="w3-button w3-black"
							href="customer-view-order.jsp?id=<%=order.getId()%>">View
								Order</a></td>
						<td>
							<%
								if (order.getStatus().equals(OrderStatus.COMPLETED.name()) && order.getFeedbackId() == 0) {
							%> <a class="w3-button w3-black"
							href="customer-feedback.jsp?id=<%=order.getId()%>">Submit
								Feedback</a> <%
 	} else if (order.getFeedbackId() > 0) {
 %><a class="w3-button w3-black"
							href="view-feedback.jsp?id=<%=order.getFeedbackId()%>">View
								Feedback</a> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>