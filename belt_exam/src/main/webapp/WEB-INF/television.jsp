<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tacos</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Welcome, ${thisLoggedInUser.userName }</h1>
	<h3>TV Shows</h3>
	<a href="/new">Add a Show</a>
	<a href="/logout">Logout</a>
	<div>
		<c:forEach items="${allTelevisions }" var="t">
			<table class="table">
				<tr>
					<th scope="col">Show</th>
					<th scope="col">Network</th>
				</tr>
				<tr>
					<td><a href="/televisions/${t.id}">${t.title}</a></td>
					<td>${t.network}</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>