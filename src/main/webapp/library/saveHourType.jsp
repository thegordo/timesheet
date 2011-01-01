<%@ page import="timeSheet.database.entity.HourType" %>
<%@ page import="timeSheet.database.manager.HourTypeManager" %>
<%@ page import="javax.persistence.PersistenceException" %>
<%
    HourTypeManager manager = new HourTypeManager();
    HourType type = new HourType();
    String idString = request.getParameter(HourType.Field.id.toString());
    int id = 0;
    if (idString != null) {
        id = Integer.parseInt(idString);
    }
    if (id > 0) {
        type = manager.getType(id);
    }

    type.setDefaultFlag(Boolean.parseBoolean(request.getParameter(HourType.Field.defaultFlag.toString())));
    type.setPaid(Boolean.parseBoolean(request.getParameter(HourType.Field.paid.toString())));
    type.setName(request.getParameter(HourType.Field.name.toString()));

    try {
        manager.saveType(type);
%>
        <script type="text/javascript">window.location.replace("../manageHourTypes.jsp")</script>
<%
    } catch (PersistenceException e) {
        out.println(e.getMessage());
    }
%>
