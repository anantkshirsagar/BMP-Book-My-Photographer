<%@page import="com.bmp.utils.AppConstants.OrderStatus"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="com.tables.Order"%>
<%@page import="com.tables.Photographer"%>
<%@page import="com.services.AdminService"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" import="java.util.*,java.time.*"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html lang="en">
<head>
<title>View Calender</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/photographer-nav-bar.html'"></div>
</head>
<body ng-app="">
	<div class="container">
		<br>
		<%
			boolean isNextMonth = Boolean.valueOf(request.getParameter("isNextMonth"));
		%>
		<div class="row">
			<div class="col-md-2">
				<%
					if (isNextMonth) {
				%>
				<a class="w3-button w3-green"
					href="photographer-view-photographer-calender.jsp">Previous</a>
				<%
					}
				%>
			</div>
			<div class="col-md-9">
				<center>
					<h3>
						<%=isNextMonth ? StringUtils.capitalize(LocalDate.now().getMonth().plus(1).name().toLowerCase())
					: StringUtils.capitalize(LocalDate.now().getMonth().name().toLowerCase())%></h3>
				</center>
			</div>
			<div class="col-md-1">
				<%
					if (!isNextMonth) {
				%>
				<a class="w3-button w3-green"
					href="photographer-view-photographer-calender.jsp?isNextMonth=true">Next</a>
				<%
					}
				%>
			</div>
		</div>
		<br>
		<table class="table">
			<tr class="w3-blue">
				<td align="center" width="14%">Monday</td>
				<td align="center" width="14%">Tuesday</td>
				<td align="center" width="14%">Wednesday</td>
				<td align="center" width="14%">Thursday</td>
				<td align="center" width="14%">Friday</td>
				<td align="center" width="14%">Saturday</td>
				<td align="center" width="14%">Sunday</td>
			</tr>

			<%
				AdminService adminService = new AdminService();
				String email = (String) request.getSession().getAttribute("email");
				Photographer photographer = adminService.getPhotographerByEmailId(email);
				List<Order> orderList = adminService
						.getApprovedOrdersByPhotographerId(String.valueOf(photographer.getId()));
				Map<Date, Long> orderMap = new HashMap<>();
				for (Order order : orderList) {
					if (order.getStatus().equals(OrderStatus.APPROVED.name())) {
						orderMap.put(order.getDate(), order.getId());
					}
				}
				LocalDate initial = LocalDate.now();
				if (isNextMonth) {
					initial = initial.plusMonths(1);
				}
				LocalDate start = initial.withDayOfMonth(1);
				LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
				LocalDate temp = start;
				boolean flag = true;
				int row = 0;
				int count = end.getDayOfMonth();
				if (flag) {
					flag = false;
					row = DayOfWeek.valueOf(temp.getDayOfWeek().toString()).ordinal();
					for (int j = 0; j < row; j++) {
			%>
			<td width="14%"></td>
			<%
				}
				}
				for (int i = start.getDayOfMonth(); i <= count; ++i) {
					String day = temp.getDayOfWeek().toString();
					row++;
					if (row == 8) {
						count++;
						row = 0;
			%>
			<tr></tr>
			<%
				} else {
			%><td width="14%">
				<div class="w3-card-4 w3-dark-grey"
					style="width: 100%; object-fit: cover;">
					<div class="w3-container w3-center">
						<br>
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
						<br> <br>
						<%
							if (orderMap.get(Date.valueOf(temp)) != null) {
						%>
						<a class="w3-button w3-green"
							href="photographer-view-order.jsp?id=<%=orderMap.get(Date.valueOf(temp))%>">View
							Order</a> <br>
						<%
							} else {
						%>
						<span><b>Not Booked</b></span> <br> <br>
						<%
							}
						%>
						<br>
					</div>
				</div>
			</td>

			<%
				temp = temp.plusDays(1);
					}
				}
			%>
		</table>
	</div>
</body>
</html>
