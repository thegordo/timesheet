package timeSheet;

import timeSheet.database.entity.Employee;

import javax.servlet.http.HttpSession;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 12:43 AM
 */
public class Dashboard {
    private HttpSession session;

    public Dashboard(HttpSession session) {
        this.session = session;
    }

    public String getMenu() {
        String menu = "";
        Employee currentEmployee = (Employee) session.getAttribute(SessionConst.employee.toString());
        switch (currentEmployee.getRole()) {
            case Administrator:
                menu += "<a href=\"manageGroups.jsp\">Manage Groups</a><br />";
                menu += "<a href=\"manageEmployees.jsp\">Manage Employees</a><br />";
                menu += "<a href=\"manageSettings.jsp\">Manage Settings</a><br />";
            case Executive:
                menu += "<a href=\"reports/reports.jsp\">Reports</a><br />";
            case Manager:
            case AssistantManager:
            case TimeSheetApproval:
                menu += "<a href=\"manageTime.jsp\">Manage Time</a><br />";
            case Employee:
                menu += "<a href=\"timeEntering.jsp\">Enter Time</a><br />";
                menu += "<a href=\"manageUser.jsp\">Manage Account</a><br />";

        }

        return menu;
    }

    public String getName() {
        return ((Employee)session.getAttribute(SessionConst.employee.toString())).getName();
    }
}
