<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.HourType" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%@ page import="java.util.List" %>
<%--
  User: John Lawrence
  Date: 12/29/10
  Time: 1:52 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, true)) return; %>
    <% HourTypeManager groupManager = new HourTypeManager(); %>
    <title>PaySystem - Manage Hour Types</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<timeSheet:header sub="Hour Type Management"/>

<div>
    <table class="install">
        <%
            List<HourType> hourTypeList = groupManager.getList();
            for (HourType hourType : hourTypeList) {
        %>
        <tr>
            <td class="value"><%=hourType.getName()%></td>
            <td class="action"><a href="editHourType.jsp?<%= HourType.Field.id%>=<%=hourType.getId()%>">Edit</a></td>
            <td class="action"><a href="library/deleteHourType.jsp?<%=HourType.Field.id%>=<%=hourType.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<div class="login">
    <a href="editHourType.jsp">Add</a>
</div>
<timeSheet:footer />
</body>
</html>
