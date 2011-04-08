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
    <% if (UtilWeb.checkSession(out, request, true)) return; %>
    <% GroupManager groupManager = new GroupManager(); %>
    <title>PaySystem - Manage Groups</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<timeSheet:header sub="Group Management"/>

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
