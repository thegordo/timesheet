<%@ page import="timeSheet.PayPeriod" %>
<%@ page import="timeSheet.TimeEntering" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.util.logging.Logger" %>
<%--
  User: John Lawrence
  Date: 3/10/11
  Time: 10:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmployeeManager empManager = new EmployeeManager();
    Employee sessionEmployee = UtilWeb.getSessionEmployee(session);
    PayPeriod payPeriod = new PayPeriod();
    Employee employee = empManager.getEmployee(Integer.parseInt(request.getParameter("empID")));

    if (request.getParameter("date") != null) {
        try {
            payPeriod = new PayPeriod(UtilWeb.getDateFormat().parse(request.getParameter("date")));
        } catch (ParseException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    TimeEntering lib = new TimeEntering(sessionEmployee, employee, payPeriod);

    StringBuilder responseHtml = new StringBuilder();
    responseHtml.append(lib.getTimeTable("manageTime.jsp"));
    responseHtml.append("<button onclick=\"document.location = 'library/saveApproval.jsp?manager=true&empID=").append(employee.getId()).append("&start=").append(UtilWeb.getDateFormat().format(payPeriod.getStartDate())).append("&end=").append(UtilWeb.getDateFormat().format(payPeriod.getEndDate())).append(">Approve all for time period</button><br />");
    responseHtml.append(lib.getPayPeriodTotals());

    out.println(responseHtml.toString());

%>
