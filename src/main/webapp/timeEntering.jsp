<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.PayPeriod" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.TimeEntering" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Date" %>
<%--
  User: John Lawrence
  Date: 2/27/11
  Time: 8:51 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, false)) return; %>
    <%
        Employee employee = (Employee) session.getAttribute(SessionConst.employee.name());
        Employee sessionEmployee = employee;
        if (request.getParameter("employeeID") != null) {
            EmployeeManager manager = new EmployeeManager();
            employee = manager.getEmployee(Integer.parseInt(request.getParameter("employeeID")));
        }
        PayPeriod period = new PayPeriod();
        if (request.getParameter("date") != null) {
            Date date = null;
            try {
                date = UtilWeb.getDateFormat().parse(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            period = new PayPeriod(date);
        }

        HourTypeManager hourTypeManager = new HourTypeManager();
        TimeEntering lib = new TimeEntering(sessionEmployee, employee, period);
    %>
    <title>PaySystem - Manage User</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
    <timeSheet:menu/>
    <timeSheet:header sub="Report Time Worked"/>
    <%
        int sessionId = ((Employee) session.getAttribute(SessionConst.employee.name())).getId();
        if(employee.getId() == sessionId) {%>
            <p>You can only report time for the current pay period.</p>
      <%}
    %>
    <form action="library/saveTime.jsp" method="post">
        <div class="login">
            <label for="date">Day:</label><select class="field" id="date" name='date' tabindex="1"><%=period.getPayPeriodSelections()%></select><br/>
            <label for="type">Type:</label><select class="field" id="type" name="type" tabindex="2"><%=hourTypeManager.getOptions(false)%></select> <br/>
            <label for="hours">Hours worked:</label><input class="field" id="hours" name="hours" type="text" tabindex="3"><br/>
            <input type="hidden" name="employeeID" value="<%=employee.getId()%>">
            <input type="hidden" name="enteringID" value="<%=sessionId%>">
            <input type="hidden" name="location" value="timeEntering.jsp">
        </div>
        <button tabindex="4">Submit Hours</button>
        <br/>
    </form>
    <div class="login">
        <label for="startTime">Time Started(h:m):</label>
        <label class="opposite" for="startPM">PM</label><input class="field" type="checkbox" id="startPM" tabindex="6">
        <input class="field" id="startTime" type="text" tabindex="5"><br/>
        <label for="endTime">Time Ended(h:m):</label>
        <label class="opposite" for="endPM">PM</label><input class="field" type="checkbox" id="endPM" checked tabindex="8">
        <input class="field" id="endTime" type="text" tabindex="7"><br/>
        <label for="lunchTime">Duration of Lunch(h:m):</label><input style="width:202px;" class="field" id="lunchTime" type="text" tabindex="9"><br/>
        <button onclick="calculateTime();" tabindex="9">Calculate Time</button>
    </div>
    <br/>
    <%
        out.println(lib.getTimeTable("timeEntering.jsp"));
        out.println(lib.getPayPeriodTotals());
    %>
    <timeSheet:footer />
</body>
</html>
