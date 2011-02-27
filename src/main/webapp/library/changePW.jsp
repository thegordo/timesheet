<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.util.SHA" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%--
  User: John Lawrence
  Date: 2/26/11
  Time: 11:59 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmployeeManager manager = new EmployeeManager();
    int employeeID = Integer.parseInt(request.getParameter("id"));
    Employee employee = manager.getEmployee(employeeID);

    String passwordParameter = request.getParameter("password");
    if (passwordParameter != null) {
        SHA sha1 = new SHA(passwordParameter);
        String password = sha1.toString();
        employee.setPassword(password);
    }

    try {
        manager.saveEmployee(employee);
        out.println("success");
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
