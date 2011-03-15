<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.Install" %>
<%@ page import="timeSheet.SessionConst" %>
<%
    Install install = (Install) session.getAttribute(SessionConst.install.toString());
    if (install == null) {
%>
<script type="text/javascript">location.replace("index.jsp")</script>
<%
    } else {
        if (request.getParameter("LDAPServer") != null) {
            install.setLDAPServer(request.getParameter("LDAPServer"));
            install.setLDAPServer(request.getParameter("LDAPDomain"));
        }
        install.doInstall();
    }
    session.invalidate();
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
    <p>Congratulations, PaySystem has been successfully installed.  Please <a href="../index.jsp">login</a>.</p>
</div>
<timeSheet:footer />
</body>
</html>
