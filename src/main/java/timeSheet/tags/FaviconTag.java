package timeSheet.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 4/1/11
 * Time: 1:13 AM
 */
public class FaviconTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().println("<link rel=\"icon\" href=\"img/icon_timesheet.png\" type=\"image/png\" />\n" +
                    "<link rel=\"shortcut icon\" href=\"img/icon_timesheet.png\" tyspe=\"image/png\" />");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return super.doStartTag();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
