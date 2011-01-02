<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.UtilWeb" %>
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

<form action="installDatabase.jsp">
    <div class="login">
        <label for="companyName">Company Name:</label>
        <input class="field" type="text" id="companyName" name="companyName"><br/>
        <br/>
    </div>
    <div class="installInstruction">
        In order to install we need to get some information about your database system. <br/>
        Please make sure that the directory in which the pay system files are writable by the apache user.<br/>
        Using a tool of your choice, please also create a database and user/password in MySQL for PaySystem and then
        enter the information below.
    </div>

    <div class="login">
        <label for="h2">H2</label><input class="field" type="radio" name="dbType" value="H2" id="h2" checked="true"
                                         onclick="setupDBInputs()"/><br/>
        <label for="mysql">MySQL</label><input class="field" type="radio" name="dbType" value="MySQL" id="mysql"
                                               onclick="setupDBInputs()"/><br/>
    </div>

    <div class="login" id="mySqlChoice"></div>
    <div class="login">
        <label for="DBUserName">Database user name:</label><input class="field" type="text" id="DBUserName"
                                                                  name="DBUserName"/><br/>
        <label for="DBPassword">Database password:</label><input class="field" type="password" id="DBPassword"
                                                                 name="DBPassword"/><br/>
    </div>
    <br/>

    <div class="installInstruction">We also need to setup an administrative user that will be the user to use for HR
        purposes. <br/>Other users and settings can be modified after the install.
    </div>
    <br/>

    <div class="login">
        <label for="adminUserName">Admin User Name:</label><input class="field" type="text" id="adminUserName"
                                                                  name="adminUserName" value="admin"
                                                                  readonly="readonly"/><br/>
        <label for="adminPassword">Password:</label><input class="field" type="password" id="adminPassword"
                                                           onkeyup="checkPassword()"/><br/>
        <label for="adminPassword2">Password(again):</label><input class="field" type="password" id="adminPassword2"
                                                                   onkeyup="checkPassword()"/><br/>
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
