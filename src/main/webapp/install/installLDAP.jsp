<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.util.SHA" %>
<%--
  User: John Lawrence
  Date: 1/2/11
  Time: 4:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Need to notify the install object of all databaseParameters.
    Install install = (Install) session.getAttribute(SessionConst.install.toString());
    if (install == null) {
%>
<script type="text/javascript">location.replace("index.jsp")</script>
<%
    } else {
        install.setAdminName(request.getParameter("adminName"));
        install.setAdminUserName(request.getParameter("adminUserName"));
        install.setAdminPassword(new SHA(request.getParameter("adminPassword")).toString());
        if (request.getParameter("ldapLogin") == null ) {
%>
<script type="text/javascript">location.replace("success.jsp")</script>
<%
        }
    }

%>
<html>
<head>
    <title>Pay System Install</title>
    <style type="text/css">
        @import url('../display.css');
    </style>
    <script type="text/javascript" src="scripts.js"></script>
</head>
<body>
<h1>Pay System Installer</h1>

<form action="success.jsp" method="post">
    <div class="installInstruction">We also need to setup an administrative user that will be the user to use for HR
        purposes. <br/>Other users and settings can be modified after the install.
    </div>
    <br/>

    <div class="login">
        <label for="adminName">Name:</label><input class="field" type="text" id="adminName" name="adminName"><br/>
        <label for="adminUserName">Admin User Name:</label><input class="field" type="text" id="adminUserName" name="adminUserName" value="admin"/><br/>
        <label for="adminPassword">Password:</label><input class="field" type="password" id="adminPassword" onkeyup="checkPassword()"/><br/>
        <label for="adminPassword2">Password(again):</label><input class="field" type="password" id="adminPassword2" onkeyup="checkPassword()"/><br/>
    </div>
    <div id="passwordVerification"></div>
    <br/>

    <div>
        <input class="submit" type="submit" value="Install">
    </div>
</form>
<% out.println(UtilWeb.getFooter()); %>
</body>
</html>
