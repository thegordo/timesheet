<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.SessionConst" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%--
  User: John Lawrence
  Date: 2/26/11
  Time: 10:33 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, false)) return; %>
    <%
        Employee employee = (Employee) session.getAttribute(SessionConst.employee.name());
    %>
    <title>PaySystem - Manage User</title>
    <timeSheet:headDefault displayCalendar="false"/>
</head>
<body>
    <timeSheet:menu/>
    <timeSheet:header sub="User Management"/>

    <form action="library/saveUser.jsp" method="post" name="employee">
        <div class="login">
            <label for="fileNum">Wage:</label>
            <input class="field" id="fileNum" name="<%=Employee.Field.wage%>"
                   type="text" <%= employee != null && employee.getWage() != null ? "value=\"" + employee.getWage() + "\"" : "\"\"" %> /><br />
            <button>Submit</button>
        </div>
    </form>

    <a href='dashboard.jsp'>Cancel</a><br/>

    <%=employee == null ? "" : "<button onclick=\"changePassword(" + employee.getId() + ");\" id = \"changePWButton\">Change Password</button>"%>
    <div id="changePassword"></div>
    <timeSheet:footer/>
</body>
</html>
