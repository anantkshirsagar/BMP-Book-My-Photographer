<%@page import="java.util.Base64"%>
<%@page import="com.model.FileContent"%>
<%@page import="com.services.AdminService"%>
<%@page import="com.tables.Photographer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="w3-table-all">
		<thead>
			<tr class="w3-green">
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<FileContent> fileContentList = new AdminService().getPhotosByIdPhotographer(1);
				for (FileContent file : fileContentList) {
			%>
			<tr>
				<td><%=file.getFileName()%></td>
				<td><img id="profileImage"
					src="data:image/jpg;base64,<%Base64.getEncoder().encode(file.getBytes());%> ">
			</tr>
			<tr>
				<img src="images/<%=file.getFileName()%>">
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>