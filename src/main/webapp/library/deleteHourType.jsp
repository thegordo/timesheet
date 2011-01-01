<%@ page import="timeSheet.database.entity.HourType" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%@ page import="javax.persistence.PersistenceException" %>

<%
    HourTypeManager manager = new HourTypeManager();
    try {
        manager.deleteType(Integer.parseInt(request.getParameter(HourType.Field.id.toString())));
%>
    <script type="text/javascript">window.location.replace("../manageHourTypes.jsp");</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
