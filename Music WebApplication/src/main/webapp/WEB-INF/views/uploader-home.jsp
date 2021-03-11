<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uploader-Home</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="row">
<h2>
	&nbsp;&nbsp;${uploader.firstName} ${ uploader.lastName}
<a class="btn btn-danger pull-right"  href="${contextPath}/user/logout">LOGOUT</a>
</h2>
</div>

<h4>Songs uploaded by you:</h4>	<a href="${contextPath}/track/uploadNewTrack">Click Here To Upload A New Track</a><br/>

	<table class="table">
		<tr>
			<td><b>Artist</b></td>
			<td><b>Title</b></td>
			<td><b>URL</b></td>
		</tr>
		<c:forEach var="track" items="${tracksList}">
			<tr>
				<td>${track.artist}</td>
				<td>${track.title}</td>
				<td><a href="${track.URL}">${track.URL}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>