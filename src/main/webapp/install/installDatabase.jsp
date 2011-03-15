<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.database.DBType" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/21/10
  Time: 1:04 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Install install = (Install) session.getAttribute(SessionConst.install.toString());
    if (install != null) {
        install.setCompanyName(request.getParameter("companyName"));
    } else {
%>
<script type="text/javascript">location.replace("index.jsp")</script>
<%
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


    <form action="installUser.jsp" method="post">
        <div class="login">
            <p>Next up we need to get some information about your desired database system.</p>
            <p>We currently have a choice to work with 2 different databases, H2 and MySQL, and we can connect to the H2
                database either through and embedded connection or a TCP connection.</p>
        </div>
        <div class="login">
            <label for="h2">H2</label><input class="field" type="radio" name="dbType" value="<%=DBType.H2.toString()%>" id="h2" checked="true" onclick="setupDBInputs()"/><br/>
            <label for="h2E">H2 Embedded</label><input class="field" type="radio" name="dbType" value="<%=DBType.H2Embedded.toString()%>" id="h2E" onclick="setupDBInputs()"/><br/>
            <label for="mysql">MySQL</label><input class="field" type="radio" name="dbType" value="<%=DBType.MySQL.toString()%>" id="mysql" onclick="setupDBInputs()"/><br/>
        </div>
        <br />
        <div class="login">
            <label for="DBLocation" id="dbChoice">Database Location:</label><input class="field" type="text" id="DBLocation" name="DBLocation" value="/~/.PaySystem/paySystem"/><br />
            <label for="DBUserName">Database user name:</label><input class="field" type="text" id="DBUserName" name="DBUserName"/><br/>
            <label for="DBPassword">Database password:</label><input class="field" type="password" id="DBPassword" name="DBPassword"/><br/>
        </div>
        <br/>
        <div>
                <input class="submit" type="submit" value="Next">
        </div>
    </form>

    <timeSheet:footer />
</body>
</html>
