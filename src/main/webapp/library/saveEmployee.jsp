<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.entity.GroupRole" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.util.SHA" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  User: John Lawrence
  Date: 12/29/10
  Time: 1:03 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SimpleDateFormat dateFormat = UtilWeb.getDateFormat();
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee();
    int id = Integer.parseInt(request.getParameter(Employee.Field.id.toString()));
    if (id > 0) {
        employee = manager.getEmployee(id);
    } else {
        SHA sha1 = new SHA(request.getParameter(Employee.Field.pass1.toString()));
        String password = sha1.toString();
        employee.setPassword(password);
    }
    employee.setActiveFlag(Boolean.parseBoolean(request.getParameter(Employee.Field.active.toString())));
    employee.setSalary(Boolean.parseBoolean(request.getParameter(Employee.Field.salaried.toString())));
    employee.setPtoAllowed(Boolean.parseBoolean(request.getParameter(Employee.Field.ptoAllowed.toString())));
    employee.setEmailAddress(request.getParameter(Employee.Field.email.toString()));
    employee.setFileNumber(request.getParameter(Employee.Field.fileNum.toString()));
    employee.setName(request.getParameter(Employee.Field.name.toString()));
    employee.setUserName(request.getParameter(Employee.Field.userName.toString()));
    employee.setRole(GroupRole.valueOf(request.getParameter(Employee.Field.role.toString())));
    try {
        employee.setFullTimeDate(dateFormat.parse(request.getParameter(Employee.Field.fullTimeDate.toString())));
        employee.setHireDate(dateFormat.parse(request.getParameter(Employee.Field.dateHired.toString())));
    } catch (ParseException e) {
        e.printStackTrace();
    }

    try {
        manager.saveEmployee(employee);
%>
<script type="text/javascript">window.location.replace("../manageEmployees.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
