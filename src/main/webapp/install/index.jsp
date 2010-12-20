<%@ page import="timeSheet.UtilWeb" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/19/10
  Time: 9:10 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pay System Install</title>
    <style type="text/css">
        @import url('../display.css');
    </style>
    <script type="text/javascript" src="../scripts.js"></script>
</head>
<body>
    <h1>Pay System Installer</h1>

    <div class="installInstruction">
        In order to install we need to get some information about your database system. <br/>
        Please make sure that the directory in which the pay system files are writable by the apache user.<br/>
        Using a tool of your choice, please also create a database and user/password in MySQL for PaySystem and then
        enter the information below.
    </div>
    <br/>

    <div class="login">
        <label for="DBName">Database Name:</label><input class="field" type="text" id="DBName" name="DBName"/><br/>
        <label for="DBUserName">Database user name:</label><input class="field" type="text" id="DBUserName" name="DBUserName"/><br/>
        <label for="DBPassword">Database password:</label><input class="field" type="password" id="DBPassword" name="DBPassword"/><br/>
    </div>
    <br/>

    <div class="installInstruction">We also need to setup an administrative user that will be the user to use for HR
        purposes. <br/>Other users and settings can be modified after the install.
    </div>
    <br/>

    <div class="login">
        <label for="adminUserName">Admin User Name:</label><input class="field" type="text" id="adminUserName" name="adminUserName" value="admin" readonly="readonly"/><br/>
        <label for="adminPassword">Password:</label><input class="field" type="password" id="adminPassword" onkeyup="checkPassword()"/><br/>
        <label for="adminPassword2">Password(again):</label><input class="field" type="password" id="adminPassword2" onkeyup="checkPassword()"/><br/>
    </div>
    <div id="passwordVerification"></div>
    <br/>

    <div>
        <button onclick="doInstall();">Submit</button>
    </div>
    <% out.println(UtilWeb.getFooter()); %>
</body>
</html>
