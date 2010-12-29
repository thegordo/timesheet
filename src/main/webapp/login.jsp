<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.util.SHA" %>
<%@ page import="java.util.logging.Logger" %>
<%--
  User: John Lawrence
  Date: Dec 5, 2010
  Time: 2:06:38 AM
--%>
<%
	String username = request.getParameter("userName");
    SHA pass = new SHA(request.getParameter("password"));
    String password = pass.toString();
	Logger.getLogger("login").info("Attempting to login user " + username);
	if (username == null || password == null) {
		out.print("Invalid parameters ");
	} else {
        EmployeeManager manager = new EmployeeManager();
        Employee employee = manager.getEmployee(username);
        if (employee.getPassword().equals(password)) {
            session.setAttribute(SessionConst.employee.toString(), employee);
            out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
        } else {
            out.println("Invalid username and password");
            out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
        }
    }
%>
