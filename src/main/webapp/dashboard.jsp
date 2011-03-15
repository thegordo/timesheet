<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.Dashboard" %>
<%@ page import="timeSheet.UtilWeb" %>
<%--
  User: John Lawrence
  Date: Dec 5, 2010
  Time: 2:10:30 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% UtilWeb.checkSession(session, out, false, false); %>
    <% Dashboard dashboard = new Dashboard(session); %>
    <title>PaySystem - Dashboard</title>
    <style type="text/css">
        @import url('display.css');
    </style>
</head>
<body>
<% out.println(UtilWeb.getMenu(request));%>
<h1><%=UtilWeb.getCompanyName()%> Pay System</h1>

<h2>Dashboard - <%=dashboard.getName()%>
</h2>

<div id="content"><%out.println(dashboard.getMenu()); %></div>
<timeSheet:footer />
</body>
</html>
