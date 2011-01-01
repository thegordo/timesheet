<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%--
  User: John Lawrence
  Date: 12/29/10
  Time: 12:58 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String empId = request.getParameter(Employee.Field.id.toString());
    int id = Integer.parseInt(empId);
    EmployeeManager manager = new EmployeeManager();
    try {
        manager.deleteEmployee(id);
%>
    <script type="text/javascript">window.location.replace("../manageEmployees.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>

