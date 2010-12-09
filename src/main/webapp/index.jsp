<%@ page import="timeSheet.UtilWeb" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
        <% UtilWeb.checkSession(session, out, true); %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			@import url('display.css');
		</style>
		<title>PaySystem</title>
	</head>
	<body>
		<h1>Fishbowl Pay System</h1>
		<div class="login">
			<form action="login.jsp" method="post">
				<label for="userName">User Name:</label>
				<input type="text" class="input" name="userName" id="userName" >
				<br /><br />
				<label for="password">Password:</label>
				<input type="password" class="input" name="password" id="password" >
				<br /><br />
				<input type="submit" value="Login">
			</form>
		</div>
		<div id="footer">
			<h6><br />&copy; 2010 by John Lawrence. <br/>Licensed under the <a href="http://www.gnu.org/licenses/gpl.html">GPL</a></h6>
		</div>
	</body>
</html>
