<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="com.servlet.*,com.tables.*;" pageEncoding="ISO-8859-1"%>
<%
	User user = (User) request.getAttribute("user");
	out.println(user);
%>