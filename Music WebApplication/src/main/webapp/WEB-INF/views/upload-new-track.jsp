<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload A New Track</title>
</head>
<body>
<div class="container-fluid">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row">
<h2>
<form action="${contextPath}/user/login" method="post"><input class="btn btn-primary" type="submit" value="Back"/></form><br/>
	${uploader.firstName} ${ uploader.lastName}
<a class="btn btn-danger pull-right"  href="${contextPath}/user/logout">LOGOUT</a>
</h2>
</div>
	
<c:if test="${t != null}">
<c:out value="${error}"/>
</c:if>

	<c:out value="${requestScope.uploaded}"/>
	<form:form action="${contextPath}/track/uploadNewTrack"
		commandName="track" method="post">

		<table class="table">
			<tr>
				<td>Artist Name:</td>
				<td><form:input path="artist" size="30" required="required" />
					<font color="red"><form:errors path="artist" /></font></td>
			</tr>

			<tr>
				<td>Song Title:</td>
				<td><form:input path="title" size="30" required="required" />
					<font color="red"><form:errors path="title" /></font></td>
			</tr>


			<tr>
				<td>URL:</td>
				<td><form:input path="URL" size="30" required="required" /> <font
					color="red"><form:errors path="URL" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input class="btn btn-primary" type="submit" value="Upload" /></td>
			</tr>
		</table>
	<c:set var="uploaded" scope="request" value="uploaded"/>
	</form:form>
</div>
</body>
</html>