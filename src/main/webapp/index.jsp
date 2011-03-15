<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:useBean id="creds" class="timeSheet.form.Credentials" scope="request"/>
<%
    // If process is true, attempt to validate and process the form
    String error = null;
    if ("true".equals(request.getParameter("process"))) {
%>
<jsp:setProperty name="creds" property="*"/>
<%
        // Attempt to process the form
        error = creds.process(session);
        if (error == null) {
%>
<jsp:forward page="dashboard.jsp"/>
<%
        }
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <% UtilWeb.checkInstall(out); %>
        <% UtilWeb.checkSession(session, out, true, false); %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			@import url('display.css');
		</style>
		<title>PaySystem</title>
	</head>
	<body>
		<h1><%=UtilWeb.getCompanyName()%> Pay System</h1>
        <timeSheet:error error="<%=error%>" />
		<div class="login">
			<form action="<%=request.getRequestURI()%>" method="post">
				<label for="userName">User Name:</label>
				<input type="text" class="input" name="userName" id="userName" >
				<br /><br />
				<label for="password">Password:</label>
				<input type="password" class="input" name="password" id="password" >
				<br /><br />
				<input class="submit" type="submit" value="Login">
                <input type="HIDDEN" name="process" value="true">
			</form>
		</div>
        <timeSheet:footer />
	</body>
</html>
