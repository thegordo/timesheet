<%@ page import="timeSheet.database.entity.EmployeeGroup" %>
<%@ page import="timeSheet.database.manager.GroupManager" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%--
  User: John Lawrence
  Date: 12/27/10
  Time: 9:46 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GroupManager manager = new GroupManager();
    try {
        manager.deleteGroup(Integer.parseInt(request.getParameter(EmployeeGroup.Field.id.toString())));
%>
    <script type="text/javascript">window.location.replace("../manageGroups.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
