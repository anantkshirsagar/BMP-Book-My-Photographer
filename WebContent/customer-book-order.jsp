<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.html");
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
<title>Book Order</title>
</head>
<%
	String photographerId = request.getParameter("id");
	String dateStr = request.getParameter("date");
	/* Date date = new Date(Long.valueOf(dateStr));
	System.out.print(date); */
	session.setAttribute("photographerId", photographerId);
%>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
			<form action="CustomerBookOrder?date=<%=dateStr%>" method="post">
				<h3>
					<b>Order Details</b>
				</h3>
				<br>
				<div class="row">
					<div class="col-md-6">
						<label>Title</label> <input class="w3-input" type="text"
							name="title" id="title" required>
					</div>
					<div class="col-md-3">
						<label>Order Date</label> <input class="w3-input" type="text"
							name="date" id="date" disabled value="<%=dateStr%>">
						<%-- <input type="date" name="date"
							value="<%=date%>" id="title" disabled
							pattern="MM/dd/yyyy - HH:mm:ss"> --%>
					</div>
					<div class="col-md-2">
						<label>Order Time </label><input type="time" name="time"
							id="title" required>
						<%-- <div class="row">
							<select placeholder="HH" name="hours" required>
								<option disabled selected>HH</option>
								<%
									for (int i = 1; i < 25; i++) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>
							</select> <select name="minutes" required>
								<option disabled selected>MM</option>
								<%
									for (int i = 1; i < 61; i++) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>
							</select>
						</div> --%>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<label>Address</label> <input class="w3-input" type="text"
							name="address" id="address" required>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<label>Notes</label>
						<textarea style="float: left; width: 100%; resize: none;" rows="5"
							name="note">
						</textarea>
					</div>
				</div>
				<br> <br>
				<button type="submit" class="w3-button w3-blue w3-large">Submit
					Order</button>
				<button type="reset" class="w3-button w3-black w3-large">Clear</button>
				<br> <br> <br>
			</form>
		</div>
	</div>
</body>
</html>