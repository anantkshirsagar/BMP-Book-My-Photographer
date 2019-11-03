<%@page import="com.bmp.utils.AppConstants.PhotographerStatus"%>
<%@page import="java.util.Base64"%>
<%@page import="com.model.FileContent"%>
<%@page import="com.services.AdminService"%>
<%@page import="com.tables.Photographer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect(request.getContextPath() + "/logout.jsp");
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
<div ng-include="'commons/admin-nav-bar.html'"></div>
<script src="js/commons.js"></script>
<title>Insert title here</title>
</head>
<%
	String id = request.getParameter("id");
	Photographer photographer = new AdminService().getPhotographerById(id);
	String functionToLoad = "";
	List<FileContent> fileContentList = new AdminService().getPhotosByPhotographerId(id);
	for (FileContent file : fileContentList) {
		functionToLoad = functionToLoad + "showImage(" + file.getFileName() + ", 'ImageServlet?photoId="
				+ file.getFileName() + "');";
	}
%>
<body ng-app="" onload="<%=functionToLoad%>;">
	<div class="w3-container">
		<div class="w3-container">
			<div class="w3-container">
				<div class="w3-container">
					<br> <br>
					<div class="row">
						<div class="col-md-6">
							<label>Name</label> <input class="w3-input" type="text"
								name="name" id="name" value="<%=photographer.getName()%>"
								disabled>
						</div>
						<div class="col-md-6">
							<label>E-mail</label> <input class="w3-input" type="email"
								name="email" id="email" value="<%=photographer.getEmail()%>"
								disabled>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-6">
							<label>Mobile No</label> <input class="w3-input" type="number"
								name="mobileNo" id="mobileNo"
								value="<%=photographer.getMobileNo()%>" disabled>
						</div>
						<div class="col-md-6">
							<label>City</label> <input class="w3-input" type="text"
								name="city" id="city"
								value="<%=photographer.getCity() != null ? photographer.getCity() : ""%>"
								disabled>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-12">
							<label>City</label> <input class="w3-input" type="text"
								name="category" id="category"
								value="<%=photographer.getCategory() != null ? photographer.getCategory() : ""%>"
								disabled>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-10"></div>
						<div class="col-md-2">
							<button class="w3-button w3-block w3-teal"
								onclick="<%=functionToLoad%>;">Click to Reload photos</button>
						</div>
					</div>
					<div class="row" id="photoDiv">
						<%
							for (FileContent file : fileContentList) {
						%>
						<div class="col-md-3" style="padding-top: 20px;">
							<div class="w3-card-4">
								<img src="" id="<%=file.getFileName()%>"
									style="width: 100%; height: 20vw; object-fit: cover;">
								<div class="w3-container w3-center"></div>
							</div>
						</div>
						<%
							}
						%>
					</div>
					<br>
					<%
						if (PhotographerStatus.SUBMITTED.name().equals(photographer.getStatus())) {
					%>
					<div class="row">
						<div class="col-md-6">
							<button class="w3-button w3-block w3-green"
								onclick="updatePhotographer(<%=id%>,'APPROVED')">Approve</button>
						</div>
						<div class="col-md-6">
							<button class="w3-button w3-block w3-red"
								onclick="updatePhotographer(<%=id%>,'REJECTED')">Reject</button>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function updatePhotographer(id,status){
			 var xhr = new XMLHttpRequest();
			 xhr.onreadystatechange = function() {
				    if (this.readyState == 4 && this.status == 200) {
				    	window.location.href = "photographer-approval-list.jsp";
				    }
				};
				xhr.open('POST', "ApproveOrRejectPhotographerServlet?id="+id+"&status="+status, true);
				xhr.send();
		}
	</script>
</body>
</html>