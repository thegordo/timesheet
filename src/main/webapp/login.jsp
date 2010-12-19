<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.manager.DatabaseManager" %>
<%--
  User: John Lawrence
  Date: Dec 5, 2010
  Time: 2:06:38 AM
--%>
<%
	String username = request.getParameter("userName");
	String password = request.getParameter("password");
	out.println("Checking login<br>");
	if (username == null || password == null) {
		out.print("Invalid parameters ");
	} else {
        DatabaseManager manager = new DatabaseManager();
        Employee employee = manager.getEmployee(username);
        if (employee.getPassword().equals(password)) {
            session.setAttribute(SessionConst.userName.toString(), username);
            session.setAttribute(SessionConst.employee.toString(), employee);
            out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
        } else {
            out.println("Invalid username and password");
        }
    }
%>
