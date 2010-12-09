<%@ page import="timeSheet.UtilWeb" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: Dec 5, 2010
  Time: 2:10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
         <% UtilWeb.checkSession(session, out); %>
		<title>PaySystem - Dashboard</title>
		<style type="text/css">
			@import url('display.css');
		</style>
	</head>
	<body>
		<?PHP echo getHeaderMenu(); ?>
		<h1>Fishbowl Pay System</h1>
		<h2>Dashboard - <%=session.getAttribute("username")%></h2>
		<div id="content"><%out.println(getOptions(session)); %></div>
		<% out.println(UtilWeb.getFooter());%>
	</body>
</html>
<%!
    private String getOptions(HttpSession session) {
        return "not doing anything yet " + session.getAttribute("username")+ "!";
    }
%>