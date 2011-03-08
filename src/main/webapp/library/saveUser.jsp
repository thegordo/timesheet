<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%--
  User: John Lawrence
  Date: 2/26/11
  Time: 11:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmployeeManager manager = new EmployeeManager();
    Employee employee = (Employee) session.getAttribute(SessionConst.employee.name());

    String wage = request.getParameter(Employee.Field.wage.toString());
    if (wage != null && !wage.isEmpty()) {
        try {
            Double wageValue = Double.parseDouble(wage);
            employee.setWage(wageValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    try {
        manager.saveEmployee(employee);
%>
<script type="text/javascript">window.location.replace("../dashboard.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
