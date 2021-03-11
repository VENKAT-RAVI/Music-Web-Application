<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <title>MyMusic</title>
</head>
<body>
<div class="contaier-fluid">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h2>Welcome to the music application.</h2>

<h3>Please login before you proceed.</h3><br/>

<div class="col-lg-4">
<form action="${contextPath}/user/login" method="post">
<label for="1">USERNAME</label><input id="1" class="form-control" type="text" name="userName" required="required"/><br/><br/>
<label for="2">PASSWORD</label><input id="2" class="form-control" type="password" name="password" required="required"/><br/><br/>
<input class="btn btn-block btn-primary" type="submit" value="Login"/>
</form>
<a class="btn btn-block btn-success" href="${contextPath}/user/register">SignUp!!</a>
</div>
</div>
</body>
</html>
