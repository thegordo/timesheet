package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.DatabaseManager;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * User: John Lawrence
 * Date: 12/8/10
 * Time: 11:42 PM
 */
public class UtilWeb implements HttpSessionListener {
    private static SimpleDateFormat simpleDateFormat;
    private static Map<String, Employee> employeeMap = new HashMap<String, Employee>();

    public static boolean checkSession(HttpSession session, JspWriter out, boolean isIndex, boolean isAdmin) {
        Employee employee = employeeMap.get(session.getId());
        if (employee == null && !isIndex) { // Check to see if the session has expired.
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (employee != null && isIndex) { // If they have an active session and We are on the index page, go to dashboard.
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (employee != null && isAdmin && !employee.isAdmin()) { // If this is an admin only page, go back to the dashboard if the employee is not admin
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        employeeMap.put(session.getId(), (Employee) httpSessionEvent.getSession().getAttribute(SessionConst.employee.toString()));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        employeeMap.remove(httpSessionEvent.getSession().getId());
    }

    public static void checkInstall(JspWriter out) {
        DatabaseManager manager = new DatabaseManager();
        if (!manager.testConnection()) {
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"install/index.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SimpleDateFormat getDateFormat() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return simpleDateFormat;
    }

    public static Employee getSessionEmployee(HttpSession session) {
        return employeeMap.get(session.getId());
    }
}
