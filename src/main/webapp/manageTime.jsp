<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%--
  User: John Lawrence
  Date: 3/8/11
  Time: 11:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <%
        HourTypeManager hourTypeManager = new HourTypeManager();
        EmployeeManager employeeManager = new EmployeeManager();
    %><% if (UtilWeb.checkSession(out, request, false)) return; %>
    <title>PaySystem - Manage Employee Time</title>
    <timeSheet:headDefault displayCalendar="true"/>
</head>
<body onload="getCalendarForEmployee();">
<%
    Employee sessionEmployee = UtilWeb.getSessionEmployee(session);
    String empID = request.getParameter("empID");
    Employee employee = null;
    if (empID != null) {
        employee = employeeManager.getEmployee(Integer.parseInt(empID));
    }
%>
<timeSheet:menu/>
<timeSheet:header sub="Manage time worked for employee"/>

<div class="login">
    <form name="inputTime" action="library/saveTime.jsp" method="post">
        <label for="fullDate">Date:</label>
        <script type="text/javascript"> tcal({'formname': 'inputTime', 'controlname': 'fullDate'});</script>
        <input class="calendarField" id="fullDate" name="fullDate" type="text" onchange="getCalendarForEmployee();"><br/>
        <label for="employeeID">Employee:</label>
        <select id="employeeID" name="employeeID" class="field" onchange="getCalendarForEmployee();">
            <% out.print(employeeManager.getEmployeeOptions(sessionEmployee, employee)); %>
        </select><br/>
        <label for="type">Type:</label><select class="field" id="type" name="type"><% out.println(hourTypeManager.getOptions(false)); %></select> <br/>
        <label for="hours">Hours:</label><input class="field" id="hours" name="hours" type="text"><br/>
        <input type="hidden" name="enteringID" id="enteringID" value="<%=sessionEmployee.getId()%>">
        <input type="hidden" name="location" id="location" value="manageTime.jsp">
        <input type="submit" value="Submit">
    </form>
</div>
<br />
<div id="calendar" class="time"></div>
<timeSheet:footer />
</body>
</html>
