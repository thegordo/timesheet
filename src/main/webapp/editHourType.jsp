<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.HourType" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%--
  User: John Lawrence
  Date: 12/29/10
  Time: 2:06 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (UtilWeb.checkSession(out, request, true)) return;
        HourTypeManager groupManager = new HourTypeManager();

        String hourTypeId = request.getParameter(HourType.Field.id.toString());
        HourType hourType = null;
        if (hourTypeId != null) {
            hourType = groupManager.getType(Integer.valueOf(hourTypeId));
        }
        String title = hourType != null ? "Edit Hour Type" : "Add Hour Type";
    %>
    <title>PaySystem - <%=title%></title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<timeSheet:header sub="<%=title%>"/>

<div class="login">
    <form action="library/saveHourType.jsp" method="post" name="hourType">
        <label for="name">Name:</label>
        <input class="field" id="name" name="<%=HourType.Field.name%>" type="text" <%= hourType == null ? "" : "value='" + hourType.getName() + "'"%> />
        <br/>
        <label for="paid"> Paid:</label>
        <input class="field" id="paid" name="<%=HourType.Field.paid%>" type="checkbox" value="TRUE" <%=hourType == null || hourType != null && hourType.getPaid() ? "checked=\"yes\"" : ""%> />
        <br/>
        <label for="defaultFlag">Default:</label>
        <input class="field" id="defaultFlag" name="<%=HourType.Field.defaultFlag%>" type="checkbox" value="TRUE"  <%=hourType != null && hourType.getDefaultFlag() ? "checked=\"yes\"" : ""%> />
        <br/>
        <input size='0' type="hidden" readonly='readonly' class='install' name='<%=HourType.Field.id%>' id="id" value=<%=hourType ==null?"\"-1\"":"\""+ hourType.getId()+"\""%>/>
        <br/>
        <input class="submit" type="submit" value="Save">
        <br />
    </form>
    <a href='manageHourTypes.jsp'>Cancel</a><br/>
</div>
<timeSheet:footer />
</body>
</html>

