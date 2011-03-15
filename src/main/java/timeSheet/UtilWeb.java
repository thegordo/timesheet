package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * User: John Lawrence
 * Date: 12/8/10
 * Time: 11:42 PM
 */
public class UtilWeb {
    private static SimpleDateFormat simpleDateFormat;

    public static void checkSession(HttpSession session, JspWriter out, boolean isIndex, boolean isAdmin) {
        Employee employee = (Employee) session.getAttribute(SessionConst.employee.toString());
        if (employee == null && !isIndex) { // Check to see if the session has expired.
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (employee != null && isIndex) { // If they have an active session and We are on the index page, go to dashboard.
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (employee != null && isAdmin && !employee.isAdmin()) { // If this is an admin only page, go back to the dashboard if the employee is not admin
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getMenu(HttpServletRequest response) {
        if (response.getRequestURI().contains("library")) {
            return "<div class=\"menu\"><a href=\"../dashboard.jsp\">Dashboard</a>&nbsp;|&nbsp;<a href=\"logout.jsp\">Logout</a></div>";
        }
        return "<div class=\"menu\"><a href=\"dashboard.jsp\">Dashboard</a>&nbsp;|&nbsp;<a href=\"logout.jsp\">Logout</a></div>";
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
        return (Employee) session.getAttribute(SessionConst.employee.name());
    }
}
