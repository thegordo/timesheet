<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/22/10
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Installation Failure</title>
    <link rel="stylesheet" type="text/css" href="../display.css"/>
</head>
<body>
<h1>Pay System Installation</h1>

<p>The following errors occurred while trying to install:</p>

<p><%out.println(request.getParameter("error"));%></p>

<p>Please correct these errors and try again.</p>
<a href="index.jsp">Go back to the install Page.</a>
<timeSheet:footer />
</body>
</html>
