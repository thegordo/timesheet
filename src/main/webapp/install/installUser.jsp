<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.DBType" %>
<%--
  User: John Lawrence
  Date: 1/2/11
  Time: 12:56 AM
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
        install.setDbType(DBType.valueOf(request.getParameter("dbType")));
        install.setDbLocation(request.getParameter("DBLocation"));
        install.setDbUserName(request.getParameter("DBUserName"));
        install.setDbPassword(request.getParameter("DBPassword"));
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

<form action="installLDAP.jsp" method="post">
    <div class="login">We also need to setup an administrative user that will be the user to use for HR
        purposes. <br/>Other users and settings can be modified after the install.
    </div>
    <br/>

    <div class="login">
        <label for="adminName">Name:</label><input class="field" type="text" id="adminName" name="adminName"><br />
        <label for="adminUserName">Admin User Name:</label><input class="field" type="text" id="adminUserName"  name="adminUserName" value="admin" /><br/>
        <label for="adminPassword">Password:</label><input class="field" type="password" id="adminPassword" name="adminPassword" onkeyup="checkPassword()"/><br/>
        <label for="adminPassword2">Password(again):</label><input class="field" type="password" id="adminPassword2" onkeyup="checkPassword()"/><br/>
    </div>
    <div id="passwordVerification"></div>
    <br/>
    <div class="login">
        <p>Would you like to use LDAP Authentication?</p>
    </div>
    <div class="login">
        <label for="ldapLogin">Use LDAP to login:</label><input class="field" type="checkbox" name="ldapLogin" id="ldapLogin" onclick="changeButtonText()"><br />
    </div>

    <br />
    <div>
        <input class="submit" type="submit" value="Install" id="submitButton">
    </div>
</form>
<timeSheet:footer />
</body>
</html>
