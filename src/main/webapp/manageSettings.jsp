<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.DBType" %>
<%@ page import="timeSheet.database.manager.SettingsManager" %>
<%@ page import="timeSheet.util.LoginType" %>
<%@ page import="timeSheet.util.PropertyName" %>
<%--
  User: John Lawrence
  Date: 1/3/11
  Time: 3:07 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SettingsManager manager = new SettingsManager();
    if (request == null) {
        out.println("We are dead!");
    } else {
        manager.saveParameters(request.getParameterMap());
    }
%>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, true)) return; %>
    <title>PaySystem - Manage System Settings</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
<timeSheet:menu/>
<timeSheet:header sub="System Settings Management"/>
<div class="login">
    <form action="manageSettings.jsp" method="post">
        <h4>Company Settings</h4>
        <br />
        <label for="companyName">Company Name:</label>
        <input class="field" type="text" id="companyName" name="<%=PropertyName.COMPANY_NAME.getName()%>" value="<%=manager.getCompanyName()%>">
        <br/>
        <label for="companyCode">Company Code:</label>
        <input class="field" type="text" id="companyCode" name="<%=PropertyName.COMPANY_CODE.getName()%>" value="<%=manager.getCompanyCode()%>">
        <br/>
        <h4>Login Settings</h4>
        <br/>
        <label for="loginType">Login Type:</label>
        <select id="loginType" name="<%=PropertyName.LOGIN_TYPE.getName()%>" class="field">
            <% out.println(LoginType.getSelection()); %>
        </select>
        <br/>
        <label for="ldapServer">LDAP Server:</label>
        <input class="field" type="text" id="ldapServer" name="<%=PropertyName.LDAP_SERVER.getName()%>" value="<%=manager.getLDAPServer()%>">
        <br/>
        <label for="ldapDomain">LDAP Domain:</label>
        <input class="field" type="text" id="ldapDomain" name="<%=PropertyName.LDAP_DOMAIN.getName()%>" value="<%=manager.getLDAPDomain()%>">
        <br/>
        <h4>Database Settings</h4>
        <br />
        <label for="dbType">Database Type:</label>
        <select id="dbType" name="<%=PropertyName.DB_TYPE.getName()%>" class="field">
            <% out.println(DBType.getSelection()); %>
        </select>
        <br />
        <label for="dbLocation">Database Location:</label>
        <input class="field" type="text" id="dbLocation" name="<%=PropertyName.DB_LOCATION.getName()%>" value="<%=manager.getDBLocation()%>">
        <br />
        <label for="dbUserName">Database User Name:</label>
        <input class="field" type="text" id="dbUserName" name="<%=PropertyName.DB_USER_NAME.getName()%>" value="<%=manager.getDBUserName()%>">
        <br />
        <label for="dbPassword">Database Password:</label>
        <input class="field" type="password" id="dbPassword" name="<%=PropertyName.DB_PASSWORD.getName()%>" value="<%=manager.getDBPassword()%>">
        <br />
        <input type="submit" value="Save" class="submit">
    </form>
</div>
<timeSheet:footer />
</body>
</html>
