<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/19/10
  Time: 9:10 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute(SessionConst.install.toString(), new Install());
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

        <div class="login">
            <p>Welcome to the Pay System Installer. We have a few things we need to know on these pages to setup everything properly for you.</p>
            <p>The first thing we will need to know is the name of your company.</p>
            <form action="installDatabase.jsp" method="post">
                <label for="companyName">Company Name:</label>
                <input class="field" type="text" id="companyName" name="companyName"><br/>
                <br/>

                <div>
                    <input class="submit" type="submit" value="Next">
                </div>
            </form>
        </div>
        <timeSheet:footer/>
    </body>
</html>
