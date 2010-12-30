<%@ page import="timeSheet.database.entity.EmployeeGroup" %>
<%@ page import="timeSheet.database.manager.GroupManager" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%--
  User: John Lawrence
  Date: 12/27/10
  Time: 8:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmployeeGroup group = new EmployeeGroup();
    group.setName(request.getParameter(EmployeeGroup.Field.name.toString()));
    int id = Integer.parseInt(request.getParameter(EmployeeGroup.Field.id.toString()));
    if (id > 0) {
        group.setId(id);
    }
    GroupManager manager = new GroupManager();
    try {
        group = manager.saveGroup(group);
        if (group == null) {
            out.println("Group already exists!");
        }
%>
        <script type="text/javascript">window.location.replace("../manageGroups.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
