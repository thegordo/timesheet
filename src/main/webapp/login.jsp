<%@ page import="timeSheet.Login" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.manager.SettingsManager" %>
<%@ page import="timeSheet.util.LoginType" %>
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
        Login login = new Login(username, password);
        SettingsManager settings = new SettingsManager();
        boolean authorized;
        if (settings.getLoginType() == LoginType.LDAP) {
            authorized = login.checkLDAPLogin(request.getParameter("password"));
        } else {
            authorized = login.checkDatabaseLogin();
        }
        if (authorized) {
            session.setAttribute(SessionConst.employee.toString(), login.getEmployee());
            out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
        } else {
            out.println("Invalid username and password");
            out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
        }
    }
%>
