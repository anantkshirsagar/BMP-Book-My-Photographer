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
%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Home</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/admin-nav-bar.html'"></div>
</head>
<%
	List<Photographer> submittedList = new AdminService()
			.getPhotographerListByStatus(PhotographerStatus.SUBMITTED.name());
	List<Photographer> approvedList = new AdminService()
			.getPhotographerListByStatus(PhotographerStatus.APPROVED.name());
	List<Photographer> rejectedList = new AdminService()
			.getPhotographerListByStatus(PhotographerStatus.REJECTED.name());
%>
<body ng-app="">
	<div class="container">
		<br>
		<h3>
			<center>
				<b>Admin Home</b>
			</center>
		</h3>
		<br>
		<div class="row">
			<div class="col-md-4">
				<div class="w3-card-4 w3-dark-grey"
					style="width: 100%; object-fit: cover;">
					<div class="w3-container w3-center">
						<br>
						<h3>Pending Approval</h3>
						<br>
						<h2>
							<%
								out.print(submittedList.size());
							%>
						</h2>
						<br> <br>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="w3-card-4 w3-dark-grey"
					style="width: 100%; object-fit: cover;">
					<div class="w3-container w3-center">
						<br>
						<h3>Approved</h3>
						<br>
						<h2>
							<%
								out.print(approvedList.size());
							%>
						</h2>
						<br> <br>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="w3-card-4 w3-dark-grey"
					style="width: 100%; object-fit: cover;">
					<div class="w3-container w3-center">
						<br>
						<h3>Rejected</h3>
						<br>
						<h2>
							<%
								out.print(rejectedList.size());
							%>
						</h2>
						<br> <br>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>