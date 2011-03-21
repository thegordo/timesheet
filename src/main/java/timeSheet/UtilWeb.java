package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
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
public class UtilWeb implements HttpSessionAttributeListener {
    private static SimpleDateFormat simpleDateFormat;
    private static Map<String, Employee> employeeMap = new HashMap<String, Employee>();

    public static boolean checkSession(JspWriter out, HttpServletRequest request, boolean isAdmin) {
        boolean isIndex = request.getRequestURI().contains("index.jsp");
        HttpSession session = request.getSession();
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

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        setupSession(httpSessionBindingEvent);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        setupSession(httpSessionBindingEvent);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        setupSession(httpSessionBindingEvent);
    }

    private void setupSession(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        Employee employee = (Employee) session.getAttribute(SessionConst.employee.toString());
        employeeMap.put(session.getId(), employee);
    }
}
