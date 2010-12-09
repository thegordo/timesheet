<%--
  Created by IntelliJ IDEA.
  User: john
  Date: Dec 5, 2010
  Time: 2:06:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%
	String username = request.getParameter("userName");
	String password = request.getParameter("password");
	out.println("Checking login<br>");
	if (username == null || password == null) {
		out.print("Invalid parameters ");
	} else if (username.toLowerCase().trim().equals("admin") && password.toLowerCase().trim().equals("admin")) {
		session.setAttribute("username", username);
		out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
	} else {
		out.println("Invalid username and password");
	}
%>