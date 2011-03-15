package timeSheet.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * User: John Lawrence
 * Date: 3/14/11
 * Time: 9:11 PM
 */
public class ErrorTag extends TagSupport {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        if (error != null) {
            this.error = error;
        } else {
            this.error = null;
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (error != null) {
                out.println();
                out.println("<div class=\"error\"><p>" + error + "</p></div>");
            }
        } catch (Exception ex) {
            throw new Error("All is not well in the world.");
        }
        // Must return SKIP_BODY because we are not supporting a body for this
        // tag.
        return SKIP_BODY;
    }
}
