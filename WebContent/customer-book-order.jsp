<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/customer-nav-bar.html'"></div>
<title>Insert title here</title>
</head>
<%
	String photographerId = request.getParameter("id");
	session.setAttribute("photographerId", photographerId);
%>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
			<form action="CustomerBookOrder" method="post">
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
						<label>Order Date</label> <input type="date" name="date"
							id="title" required>
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
				<button type="submit" class="w3-button w3-blue w3-large">Register</button>
				<button type="reset" class="w3-button w3-black w3-large">Clear</button>
				<br> <br> <br>
			</form>
		</div>
	</div>
</body>
</html>