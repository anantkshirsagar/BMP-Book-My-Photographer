<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" import="java.util.*,java.time.*"%>

<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<table class="table">
			<tr>
				<td align="center" width="14%">Sunday</td>
				<td align="center" width="14%">Monday</td>
				<td align="center" width="14%">Tuesday</td>
				<td align="center" width="14%">Wednesday</td>
				<td align="center" width="14%">Thursday</td>
				<td align="center" width="14%">Friday</td>
				<td align="center" width="14%">Saturday</td>
			</tr>

			<%
				LocalDate initial = LocalDate.now();
				LocalDate start = initial.withDayOfMonth(1);
				LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
				LocalDate temp = start;

				int row = 0;
				int count = end.getDayOfMonth();
				for (int i = start.getDayOfMonth(); i <= count; ++i) {
					String day = temp.getDayOfWeek().toString();
					row++;
					if (row == 8) {
						count++;
						row = 0;
			%><tr></tr>
			<%
				} else {
						switch (day) {
						case "SUNDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "MONDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "TUESDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "WEDNESDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "THURSDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "FRIDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						case "SATURDAY":
			%><td width="14%">
				<div class="card">
					<div class="card-body">
						<%=temp.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))%>
					</div>
				</div>
			</td>
			<%
				break;
						}

						temp = temp.plusDays(1);
					}
				}
			%>
		</table>
	</div>
</body>
</html>
