<%@ page import="timeSheet.database.entity.Hours" %>
<%@ page import="timeSheet.database.manager.HoursManager" %>
<%--
  User: John Lawrence
  Date: 3/6/11
  Time: 8:14 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HoursManager hoursManager = new HoursManager();
    boolean isManager = Boolean.parseBoolean(request.getParameter("manager"));
    if(request.getParameterMap().containsKey("id")){
        int id = Integer.parseInt(request.getParameter("id"));
        Hours hours = hoursManager.getHoursByID(id);
        if(isManager) {
            hours.setManagerApproval(true);
        } else {
            hours.setEmployeeApproval(true);
        }
        hoursManager.saveHours(hours);
    }
%>
<html>
<head></head>
<body>
<script type="text/javascript">
    window.location.replace("../<%=request.getParameter("location")%>");
</script>
</body>
</html>
