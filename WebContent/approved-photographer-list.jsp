<%@page import="com.bmp.utils.AppConstants.PhotographerStatus"%>
<%@page import="com.services.AdminService"%>
<%@page import="java.util.List"%>
<%@page import="com.tables.Photographer"%>
<%@ page language="java"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
	}
%>
<html>
<head>
<title>Approved Photographers</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script src="lib/angjs.js"></script>
<div ng-include="'commons/load-libs.html'"></div>
<div ng-include="'commons/admin-nav-bar.html'"></div>
</head>
<body ng-app="">
	<div class="jumbotron">
		<div class="container">
		<h4><center><b>Approved Photographer List</b></center></h4>
			<div class="w3-responsive">
				<table class="w3-table-all">
					<thead>
						<tr class="w3-green">
							<th>Id</th>
							<th>Name</th>
							<th>E-mail</th>
							<th>City</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Photographer> photographerList = new AdminService()
									.getPhotographerListByStatus(PhotographerStatus.APPROVED.name());
							for (Photographer photographer : photographerList) {
						%>
						<tr>
							<td><%=photographer.getId()%></td>
							<td><%=photographer.getName()%></td>
							<td><%=photographer.getEmail()%></td>
							<td><%=photographer.getCity() != null ? photographer.getCity() : ""%></td>
							<%-- <td><button type="button"
									onclick="viewProfile(<%=photographer.getId()%>)">View
									Profile</button></td> --%>
							<td><a
								href="admin-view-photographer-profile.jsp?id=<%=photographer.getId()%>">View
									Profile</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function viewProfile(id) {
			/* var xhr = new XMLHttpRequest();
			xhr.open('POST', "ViewProfile?id="+id, true);
			xhr.send(); */
		}
	</script>
</body>
</html>