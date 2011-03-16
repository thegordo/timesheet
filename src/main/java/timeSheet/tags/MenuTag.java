package timeSheet.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 3/15/11
 * Time: 9:50 PM
 */
public class MenuTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (((HttpServletRequest)pageContext.getRequest()).getRequestURI().contains("library")) {
                out.println("<div class=\"menu\"><a href=\"../dashboard.jsp\">Dashboard</a>&nbsp;|&nbsp;<a href=\"../logout.jsp\">Logout</a></div>");
            }
            out.println("<div class=\"menu\"><a href=\"dashboard.jsp\">Dashboard</a>&nbsp;|&nbsp;<a href=\"logout.jsp\">Logout</a></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
