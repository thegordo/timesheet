package timeSheet;

import timeSheet.database.manager.DatabaseManager;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 12/8/10
 * Time: 11:42 PM
 */
public class UtilWeb {
    public static void checkSession(HttpSession session, JspWriter out, boolean isIndex) {
        String userName = (String) session.getAttribute(SessionConst.userName.toString());
        if (userName == null && !isIndex) {
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userName != null && isIndex) {
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"dashboard.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFooter() {
        return "<div id=\"footer\">\n" +
                "\t\t\t<h6><br />&copy; 2010 by John Lawrence. <br/>Licensed under the <a href=\"http://www.gnu.org/licenses/gpl.html\">GPLv3</a></h6>\n" +
                "\t\t</div>";
    }

    public static String getMenu() {
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
}
