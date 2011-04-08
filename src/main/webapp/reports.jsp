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
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<% if (request.getParameter("adp") == null) { %>
<timeSheet:header sub="Reports"/>
<div class="login">
    <h3>ADP Report</h3>
    <form action="<%=request.getRequestURI()%>" method="post">
        <label for="1" >Batch ID:</label> <input class="field" type="text" name="batchId" id="1"><br />
        <label for="2" >Batch Description:</label> <input class="field" type="text" name="batchDescription" id="2"><br/>
        <input type="hidden" value="true" name="adp" />
        <input class="submit" type="submit" value="Next">
    </form>
</div>
<% } else { %>
<timeSheet:header sub="ADP Report Entry"/>
<form method="post" action="reports/adpImport.jsp">
    <timeSheet:adp />
    <input type="hidden" value="<%=request.getParameter("batchId")%>" name="batchId" />
    <input type="hidden" value="<%=request.getParameter("batchDescription")%>" name="batchDescription" />
    <input type="submit" value="Finalize Data" style="float:none;">
</form>
<% } %>
<timeSheet:footer/>
</body>
</html>
