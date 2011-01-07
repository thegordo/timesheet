<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.DBType" %>
<%@ page import="timeSheet.util.LoginType" %>
<%@ page import="timeSheet.util.PaySystemProperties" %>
<%@ page import="timeSheet.util.PropertyName" %>
<%--
  User: John Lawrence
  Date: 1/3/11
  Time: 3:07 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% UtilWeb.checkSession(session, out, false); %>
    <title>PaySystem - Manage Groups</title>
    <style type="text/css">
        @import url('display.css');
    </style>
</head>
<body>
<% out.println(UtilWeb.getMenu(request));%>
<h1><%=UtilWeb.getCompanyName()%> Pay System</h1>
<h2>Settings Management</h2>
<div class="login">
<%
    String dbLocation = PaySystemProperties.getProperty(PropertyName.DB_LOCATION);
    dbLocation = (dbLocation != null) ? dbLocation : "";
    String dbUserName = PaySystemProperties.getProperty(PropertyName.DB_USER_NAME);
    dbUserName = (dbUserName != null) ? dbUserName : "";
    String dbPassword = PaySystemProperties.getProperty(PropertyName.DB_PASSWORD);
    dbPassword = (dbPassword != null) ? dbPassword : "";
    String ldapServer = PaySystemProperties.getProperty(PropertyName.LDAP_SERVER);
    ldapServer = (ldapServer != null) ? ldapServer : "";
    String ldapDomain = PaySystemProperties.getProperty(PropertyName.LDAP_DOMAIN);
    ldapDomain = (ldapDomain != null) ? ldapDomain : "";
%>
    <form action="manageSettings.jsp" method="post">
        <h4>Database Settings</h4>
        <br />
        <label for="dbType">Database Type:</label>
        <select id="dbType" name="dbType" class="field">
            <% out.println(DBType.getSelection()); %>
        </select>
        <br />
        <label for="dbLocation">Database Location:</label>
        <input class="field" type="text" id="dbLocation" name="dbLocation" value="<%=dbLocation%>">
        <br />
        <label for="dbUserName">Database User Name:</label>
        <input class="field" type="text" id="dbUserName" name="dbUserName" value="<%=dbUserName%>">
        <br />
        <label for="dbPassword">Database Password:</label>
        <input class="field" type="password" id="dbPassword" name="dbPassword" value="<%=dbPassword%>">
        <br />
        <h4>Login Settings</h4>
        <br />
        <label for="loginType">Database Type:</label>
        <select id="loginType" name="loginType" class="field">
            <% out.println(LoginType.getSelection()); %>
        </select>
        <br />
        <label for="ldapServer">LDAP Server:</label>
        <input class="field" type="text" id="ldapServer" name="ldapServer" value="<%=ldapServer%>">
        <br />
        <label for="ldapDomain">LDAP Domain:</label>
        <input class="field" type="text" id="ldapDomain" name="ldapDomain" value="<%=ldapDomain%>">
        <br />
        <input type="submit" value="Save" class="submit">
    </form>
</div>
<% out.println(UtilWeb.getFooter());%>
</body>
</html>
