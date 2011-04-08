<%@ taglib prefix="timeSheet" uri="/WEB-INF/tags/timeSheet.tld" %>
<%@ page import="timeSheet.UtilWeb" %>
<%@ page import="timeSheet.database.entity.Employee" %>
<%@ page import="timeSheet.database.entity.GroupRole" %>
<%@ page import="timeSheet.database.manager.EmployeeManager" %>
<%@ page import="timeSheet.database.manager.GroupManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 12/25/10
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% if (UtilWeb.checkSession(out, request, true)) return; %>
    <% EmployeeManager employeeManager = new EmployeeManager(); %>
    <%
        String empID = request.getParameter(Employee.Field.id.toString());
        Employee employee = null;
        if (empID != null) {
            employee = employeeManager.getEmployee(Integer.valueOf(empID));
        }
        SimpleDateFormat format = UtilWeb.getDateFormat();
        String title = employee != null ? "Edit " + employee.getName() : "Add Employee";
    %>
    <title>PaySystem - <%=title%></title>
    <timeSheet:headDefault displayCalendar="true" />
</head>
<body>
<timeSheet:menu />
<timeSheet:header sub="<%=title%>"/>
    <form action="library/saveEmployee.jsp" method="post" name="employee">
        <div class="login">
            <label for="name">Name:</label>
            <input class="field" id="name" name="<%=Employee.Field.name%>" type="text" <%= employee == null ?"":"value='"+ employee.getName() + "'"%> />
            <br/>
            <label for="<%=Employee.Field.dateHired%>">Date Hired:</label>
            <script type="text/javascript"> new tcal({'formname': 'employee', 'controlname': '<%=Employee.Field.dateHired%>'}, A_TCALDEF);</script>
            <input class="calendarField" id="<%=Employee.Field.dateHired%>" name="<%=Employee.Field.dateHired%>" type="text" <%= employee == null ? "" : "value='" + format.format(employee.getHireDate()) + "'" %> />
            <br/>
            <label for="<%=Employee.Field.fullTimeDate%>">Full Time Date:</label>
            <script type="text/javascript">new tcal({'formname': 'employee', 'controlname': '<%=Employee.Field.fullTimeDate%>'}, A_TCALDEF);</script>
            <input class="calendarField" id="<%=Employee.Field.fullTimeDate%>" name="<%=Employee.Field.fullTimeDate%>" type="text" <%= employee == null ? "" : "value='" + format.format(employee.getFullTimeDate()) + "'" %> />
            <br/>
            <label for="group">Group:</label>
            <select class="field" id="group" name="<%=Employee.Field.group%>">
                <%
                    GroupManager groupManager = new GroupManager();
                    out.println(groupManager.getGroupSelection(employee));
                %>
            </select>
            <br/>
            <label for="role">Role:</label>
            <select class="field" id="role" name="<%=Employee.Field.role%>">
                <%
                    GroupRole role = employee != null ? employee.getRole() : null;
                    out.println(GroupRole.getHtmlOptions(role));
                %>
            </select>
            <br/>
            <label for="userName">User Name:</label>
            <input class="field" id="userName" name="<%=Employee.Field.userName%>" type="text" <%= employee == null ?"":"value='" + employee.getUserName() + "'"%> />
            <br/>
            <% if (employee == null) {
                out.println("<label for=\"pass1\">Password:</label><input class=\"field\" id=\"pass1\" name=\"" + Employee.Field.pass1 + "\" type=\"password\" /><br/>" +
                        "<label for=\"pass2\">Verify Password:</label><input class=\"field\" id=\"pass2\" name=\"" + Employee.Field.pass2 + "\" type=\"password\" /><br/>");
            }
            %>
            <label for="email">Email Address:</label>
            <input class="field" id="email" name="<%=Employee.Field.email%>" type="text" <%= employee != null && employee.getEmailAddress() != null? "value=\""+employee.getEmailAddress()+"\"" :"\"\""  %> >
            <br/>
            <label for="fileNum">File Number:</label>
            <input class="field" id="fileNum" name="<%=Employee.Field.fileNum%>" type="text" <%= employee != null && employee.getFileNumber() != null ? "value=\""+employee.getFileNumber() + "\"" : "\"\"" %> />
            <br/>
            <label for="active">Active:</label>
            <input class="field" id="active" name="<%=Employee.Field.active%>" type="checkbox" <%= employee == null || employee != null && employee.getActiveFlag() ? "checked=\"yes\"" : "" %> />
            <br/>
            <label for="ptoAllowed">PTO Allowed:</label>
            <input class="field" id="ptoAllowed" name="<%=Employee.Field.ptoAllowed%>" type="checkbox" <%= employee != null && employee.getPtoAllowed() ? "checked=\"yes\"":""%> />
            <br/>
            <label for="salaried">Salaried:</label>
            <input class="field" id="salaried" name="<%=Employee.Field.salaried%>" type="checkbox" <%= employee != null && employee.getSalary() ? "checked=\"yes\"" : ""%> />
            <br/>
            <input size='0' type="hidden" readonly='readonly' class='install' name='<%=Employee.Field.id%>' id="id" value="<%=employee==null?-1:employee.getId()%>" />
            <button>Submit</button>
        </div>
    </form>
    <a href='manageEmployees.jsp'>Cancel</a><br/>

    <%=employee == null ?"":"<button onclick=\"changePassword(" +employee.getId()+ ");\" id = \"changePWButton\">Change Password</button>"%>
    <div id="changePassword"></div>

    <timeSheet:footer />
</body>
</html>

