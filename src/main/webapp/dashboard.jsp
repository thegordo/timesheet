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
    <% if (UtilWeb.checkSession(out, request, false)) return; %>
    <title>PaySystem - Dashboard</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu />
<timeSheet:header sub='<%="Dashboard - " + UtilWeb.getSessionEmployee(session).getName()%>'/>
<timeSheet:dashMenu />
<timeSheet:footer />
</body>
</html>
