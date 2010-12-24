<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.entity.Group" %>
<%@ page import="timeSheet.database.entity.GroupRole" %>
<%@ page import="timeSheet.database.manager.DatabaseManager" %>
<%@ page import="timeSheet.util.SHA" %>
<%@ page import="java.util.Calendar" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/21/10
  Time: 1:04 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // TODO: Get the database parameters to connect with
    DatabaseManager manager = new DatabaseManager();
    manager.connect(true);

    Group adminGroup = new Group();
    adminGroup.setName("admin");

    Employee emp = new Employee();
    emp.setUserName("admin");
    emp.setName("Admin");
    emp.setActiveFlag(true);
    emp.setAdmin(true);
    emp.setHireDate(Calendar.getInstance().getTime());
    emp.setEmailAddress("");
    emp.setPassword(new SHA(request.getParameter("password")).toString());
    emp.setPtoAllowed(false);
    emp.setGroup(adminGroup);
    emp.setSalary(true);
    emp.setFullTimeDate(Calendar.getInstance().getTime());
    emp.setRole(GroupRole.Administrator);

    adminGroup.addEmployee(emp);

    manager.persist(emp);

    out.println("success");

%>
