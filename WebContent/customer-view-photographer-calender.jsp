<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" import="java.util.*,java.time.*"%>

<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
</head>
<body ng-app="">
	<div class="container">
		<table class="table">
			<tr>
				<td align="center" width="14%">Monday</td>
				<td align="center" width="14%">Tuesday</td>
				<td align="center" width="14%">Wednesday</td>
				<td align="center" width="14%">Thursday</td>
				<td align="center" width="14%">Friday</td>
				<td align="center" width="14%">Saturday</td>
				<td align="center" width="14%">Sunday</td>
			</tr>

			<%
				String id = request.getParameter("id");
				LocalDate initial = LocalDate.now();
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
						<br> <br> <a class="w3-button w3-green"
							href="customer-book-order.jsp?id=<%=id%>">Book</a> <br> <br>
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
