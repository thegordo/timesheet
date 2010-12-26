<%@ page import="timeSheet.EmployeeManager" %>
<%@ page import="timeSheet.UtilWeb" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/25/10
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% UtilWeb.checkSession(session, out, false); %>
    <% EmployeeManager employeeManager = new EmployeeManager(session); %>
    <title>PaySystem - Dashboard</title>
    <style type="text/css">
        @import url('../display.css');
    </style>
</head>
<body>
<% out.println(UtilWeb.getMenu(request));%>
<h1>Fishbowl Pay System</h1>
<h2>Edit Employee</h2>
<div class="login">
    Add the rest of it here.!
</div>
<% out.println(UtilWeb.getFooter());%>
</body>
</html>

