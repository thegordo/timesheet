package timeSheet.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 4/1/11
 * Time: 1:13 AM
 */
public class CommonHeadTag extends TagSupport {
    private boolean displayCalendar;

    public boolean isDisplayCalendar() {
        return displayCalendar;
    }

    public void setDisplayCalendar(boolean displayCalendar) {
        this.displayCalendar = displayCalendar;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder builder = new StringBuilder();
        builder.append("<style type=\"text/css\">\n" );
        builder.append("        @import url('display.css');\n");
        if (displayCalendar) {
            builder.append("        @import url(\"calendar.css\");\n");
        }
        builder.append("    </style>\n" );
        builder.append("    <script type=\"text/javascript\" src=\"scripts.js\"></script>");
        if (displayCalendar) {
            builder.append("    <script type=\"text/javascript\" src=\"calendar_db.js\"></script>");
        }
        builder.append("<link rel=\"icon\" href=\"img/icon_timesheet.png\" type=\"image/png\" />\n");
        builder.append("<link rel=\"shortcut icon\" href=\"img/icon_timesheet.png\" tyspe=\"image/png\" />");
        try {
            pageContext.getOut().println(builder.toString());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return super.doStartTag();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
