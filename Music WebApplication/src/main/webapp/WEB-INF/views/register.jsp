<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<title>New User</title>
</head>
<body>
<div class="contaier-fluid">

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a class="btn btn-primary" href="${contextPath}">Go Back</a>
	<br />

	<h3>REGISTER</h3>
	
	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">
	<input type="radio" name="userType" value="Uploader">Uploader
	<input type="radio" name="userType" value="User"/>User<br/>
		<table class="table">
			<tr>
				<td>First Name:</td><br/>
				<td><form:input class="form-control" path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input class="form-control" path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input class="form-control" path="userName" size="30" required="required" />
					<font color="red"><form:errors path="userName" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password class="form-control" path="password" size="30" 
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input class="form-control" path="email" size="30" 
						required="required" /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form:form>
</div>
</body>
</html>