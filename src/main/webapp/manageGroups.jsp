<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.EmployeeGroup" %>
<%@ page import="timeSheet.database.manager.GroupManager" %>
<%@ page import="java.util.List" %>
<%--
  User: John Lawrence
  Date: 12/27/10
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% UtilWeb.checkSession(session, out, false, true); %>
    <% GroupManager groupManager = new GroupManager(); %>
    <title>PaySystem - Manage Groups</title>
    <style type="text/css">
        @import url('display.css');
    </style>
</head>
<body>
<% out.println(UtilWeb.getMenu(request));%>
<h1><%=UtilWeb.getCompanyName()%> Pay System</h1>
<h2>Group Management</h2>

<div>
    <table class="install">
        <%
            List<EmployeeGroup> groupList = groupManager.getGroupList();
            for (EmployeeGroup group : groupList) {
        %>
        <tr>
            <td class="value"><%=group.getName()%></td>
            <td class="action"><a href="editGroup.jsp?<%=EmployeeGroup.Field.id%>=<%=group.getId()%>">Edit</a></td>
            <td class="action"><a href="library/deleteGroup.jsp?<%=EmployeeGroup.Field.id%>=<%=group.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<div class="login">
    <a href="editGroup.jsp">Add</a>
</div>
<timeSheet:footer />
</body>
</html>
