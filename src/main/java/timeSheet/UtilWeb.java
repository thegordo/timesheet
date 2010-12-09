package timeSheet;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 12/8/10
 * Time: 11:42 PM
 */
public class UtilWeb {
    public static void checkSession(HttpSession session, JspWriter out) {
        if (session.getLastAccessedTime() - session.getCreationTime() >= session.getMaxInactiveInterval()) {
            try {
                out.println("<script type=\"text/javascript\">window.location.replace(\"index.jsp\");</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFooter() {
        return "<div id=\"footer\">\n" +
                "\t\t\t<h6><br />&copy; 2010 by John Lawrence. <br/>Licensed under the <a href=\"http://www.gnu.org/licenses/gpl.html\">GPL</a></h6>\n" +
                "\t\t</div>";
    }
}
