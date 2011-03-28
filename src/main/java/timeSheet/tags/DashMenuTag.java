package timeSheet.tags;

import timeSheet.SessionConst;
import timeSheet.database.entity.Employee;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 3/15/11
 * Time: 9:58 PM
 */
public class DashMenuTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        StringBuilder sortedMenu = new StringBuilder("<div id=\"content\">");
        Employee currentEmployee = (Employee) pageContext.getSession().getAttribute(SessionConst.employee.toString());
        if (currentEmployee == null) {
            return SKIP_BODY;
        }
        switch (currentEmployee.getRole()) {
            case Administrator:
                sortedMenu.append("<a href=\"manageGroups.jsp\">Manage Groups</a><br />");
                sortedMenu.append("<a href=\"manageEmployees.jsp\">Manage Employees</a><br />");
                sortedMenu.append("<a href=\"manageSettings.jsp\">Manage Settings</a><br />");
                sortedMenu.append("<a href=\"manageHourTypes.jsp\">Manage Hour Types</a><br />");
            case Executive:
                sortedMenu.append("<a href=\"reports.jsp\">Reports</a><br />");
            case Manager:
            case AssistantManager:
            case TimeSheetApproval:
                sortedMenu.insert(0, "<a href=\"manageTime.jsp\">Manage Time</a><br />");
            case Employee:
                sortedMenu.insert(0, "<a href=\"manageUser.jsp\">Manage Account</a><br />");
                if (!currentEmployee.getSalary()) {
                    sortedMenu.insert(0, "<a href=\"timeEntering.jsp\">Enter Time</a><br />");
                }
        }
        sortedMenu.append("</div>");
        try {
            pageContext.getOut().println(sortedMenu.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
