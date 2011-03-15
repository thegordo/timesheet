package timeSheet.tags;

import timeSheet.util.PaySystemProperties;
import timeSheet.util.PropertyName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 3/14/11
 * Time: 11:28 PM
 */
public class HeaderTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String companyName = PaySystemProperties.getProperty(PropertyName.COMPANY_NAME, "");
        try {
            out.println("<h1>" + companyName + " Pay System</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
