<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
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
    <title>PaySystem - Dashboard</title>
    <style type="text/css">
        @import url('display.css');
    </style>
</head>
<body>
<timeSheet:menu />
<timeSheet:header />
<h2>Dashboard - <%=UtilWeb.getSessionEmployee(session).getName()%></h2>
<timeSheet:dashMenu />
<timeSheet:footer />
</body>
</html>
