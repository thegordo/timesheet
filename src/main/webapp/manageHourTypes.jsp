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
    <% UtilWeb.checkSession(session, out, false, true); %>
    <% HourTypeManager groupManager = new HourTypeManager(); %>
    <title>PaySystem - Manage Hour Types</title>
    <style type="text/css">
        @import url('display.css');
    </style>
</head>
<body>
<% out.println(UtilWeb.getMenu(request));%>
<h1><%=UtilWeb.getCompanyName()%> Pay System</h1>
<h2>Hour Type Management</h2>

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
<% out.println(UtilWeb.getFooter());%>
</body>
</html>
