<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.EmployeeGroup" %>
<%@ page import="timeSheet.database.manager.GroupManager" %>
<%--
  User: John Lawrence
  Date: 12/27/10
  Time: 8:26 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (UtilWeb.checkSession(out, request, true)) return;
        GroupManager groupManager = new GroupManager();

        String groupID = request.getParameter(EmployeeGroup.Field.id.toString());
        EmployeeGroup group = null;
        if (groupID != null) {
            group = groupManager.getGroup(Integer.valueOf(groupID));
        }
        String title = group != null ? "Edit Group" : "Add Group";
    %>
    <title>PaySystem - <%=title%></title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<timeSheet:header sub="<%=title%>"/>

<form action="library/saveGroup.jsp" method="post" name="employee">
    <div class="login">
        <label for="name">Name:</label>
        <input class="field" id="name" name="name" type="text" <%= group == null ? "" : "value='" + group.getName() + "'"%> />
        <br/>
        <input size='0' type="hidden" readonly='readonly' class='install' name='id' id="id" value="<%=group==null? "-1" : group.getId()%>" />
        <button>Submit</button>
    </div>
</form>
<a href='manageGroups.jsp'>Cancel</a><br/>
<timeSheet:footer />
</body>
</html>
