<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.SessionConst" %>
<%--
  User: John LAwrence
  Date: Dec 5, 2010
  Time: 2:10:30 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
         <% UtilWeb.checkSession(session, out, false); %>
		<title>PaySystem - Dashboard</title>
		<style type="text/css">
			@import url('display.css');
		</style>
	</head>
	<body>
		<% out.println(UtilWeb.getMenu());%>
		<h1>Fishbowl Pay System</h1>
		<h2>Dashboard - <%=session.getAttribute(SessionConst.userName.toString())%></h2>
		<div id="content"><%out.println(getOptions(session)); %></div>
		<% out.println(UtilWeb.getFooter());%>
	</body>
</html>
<%!
    private String getOptions(HttpSession session) {
        return "not doing anything yet " + session.getAttribute(SessionConst.userName.toString())+ "!";
    }
%>