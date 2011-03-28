<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%--
  User: John Lawrence
  Date: 3/24/11
  Time: 10:44 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, false)) return; %>
    <title>PaySystem - Manage User</title>
    <style type="text/css">
        @import url('display.css');
    </style>
    <script type="text/javascript" src="scripts.js"></script>
</head>
<body>
<timeSheet:menu/>
<% if (request.getParameter("adp") == null) { %>
<timeSheet:header sub="Reports"/>
<div class="login">
    <a href="reports.jsp?adp=true">ADP Report</a>
</div>
<% } else { %>
<timeSheet:header sub="ADP Report Entry"/>
<form method="post" action="reports/adpImport.jsp">
    <timeSheet:adp />
    <input type="submit" value="Finalize Data" style="float:none;">
</form>
<% } %>
<timeSheet:footer/>
</body>
</html>
