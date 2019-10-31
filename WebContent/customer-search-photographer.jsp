<%@page import="com.bmp.utils.AppConstants.PhotographerStatus"%>
<%@page import="com.services.AdminService"%>
<%@page import="com.tables.Photographer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%><html>
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
<body ng-app="">
	<div class="container">
		<form action="CustomerSearchPhotographer" method="get">
			<br> <br>
			<div class="row">
				<div class="col-md-4">
					<label>City</label> <input class="w3-input w3-border" type="text"
						name="city" id="city"
						value="<%=session.getAttribute("city") != null ? session.getAttribute("city") : ""%>">
				</div>
				<div class="col-md-4">
					<label>Category</label><input class="w3-input w3-border"
						type="text" name="category" id="category"
						value="<%=session.getAttribute("category") != null ? session.getAttribute("category") : ""%>">
				</div>
				<div class="col-md-4">
					<input class="w3-button w3-black" type="submit" value="Search"
						style="margin-top: 30px">
				</div>
			</div>
		</form>
		<br>
		<div class="w3-responsive">
			<table class="w3-table-all">
				<thead>
					<tr class="w3-green">
						<th>Id</th>
						<th>Name</th>
						<th>E-mail</th>
						<th>Mobile No</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Photographer> photographerList = (List<Photographer>) session.getAttribute("photographerList");
						if (photographerList != null) {
							for (Photographer photographer : photographerList) {
					%>
					<tr>
						<td><%=photographer.getId()%></td>
						<td><%=photographer.getName()%></td>
						<td><%=photographer.getEmail()%></td>
						<td><%=photographer.getMobileNo() > 0 ? photographer.getMobileNo() : ""%></td>
						<td><a href="customer-view-photographer-profile.jsp?id=<%=photographer.getId()%>">View
								Profile</a></td>
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