<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.entity.Hours" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%@ page import="timeSheet.database.manager.HoursManager" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%--
  User: John Lawrence
  Date: 3/5/11
  Time: 10:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Hours hoursWorked = new Hours();

    hoursWorked.setDateEntered(new Date());
    hoursWorked.setEmployeeApproval(true);
    hoursWorked.setManagerApproval(false);
    double hours;
    try {
        hours = Double.parseDouble(request.getParameter("hours"));
    } catch(NumberFormatException e) {
        hours = 0.0;
    }
    hoursWorked.setHours(hours);
    String day = request.getParameter("date");
    if(day != null) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        hoursWorked.setDate(cal.getTime());
    } else {
        day = request.getParameter("fullDate");
        Date date = null;
        try {
            date = UtilWeb.getDateFormat().parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoursWorked.setDate(date);
    }
    Employee employee = (Employee) session.getAttribute(SessionConst.employee.name());
    Employee sessionEmployee = (Employee) session.getAttribute(SessionConst.employee.name());
    String employeeID = request.getParameter("employeeID");
    if(employeeID != null) {
        EmployeeManager employeeManager = new EmployeeManager();
        employee = employeeManager.getEmployee(Integer.parseInt(employeeID));
    }
    hoursWorked.setEmployee(employee);
    hoursWorked.setEnteredByEmployee(sessionEmployee);
    if(!hoursWorked.isEnteredByEmployee()) { // We want to make sure that this is known as not being approved by the employee.
        hoursWorked.setEmployeeApproval(false);
        hoursWorked.setManagerApproval(true);
    }
    String type = request.getParameter("type");
    HourTypeManager man = new HourTypeManager();
    if(type != null) {
        int typeID = Integer.parseInt(type);
        hoursWorked.setType(man.getType(typeID));
    } else {
        hoursWorked.setType(man.getDefaultType());
    }
    HoursManager manager = new HoursManager();
%>
<html>
<body>
<script type="text/javascript">
<%
    try {
        hoursWorked.validate();
        manager.saveHours(hoursWorked);
        out.print("alert('Successfully submitted the hours.');");
    } catch (Exception e) {
        out.print("alert('Unable to save the hours. Reason: " + e.getMessage() + "');");
    }
%>
    window.location.replace("../<%=request.getParameter("location")%>?empID=<%=employee.getId()%>");
</script>
<a href="../dashboard.jsp">Go back to the Dashboard here.</a>
</body>
</html>
