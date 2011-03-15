package timeSheet.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 3/14/11
 * Time: 9:47 PM
 */
public class FooterTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.println(
                    "<div class='footer'>" +
                        "<h6><br />&copy; 2010 by John Lawrence. <br/>Licensed under the <a href=\"http://www.gnu.org/licenses/gpl.html\" target=\"_blank\" >GPLv3</a></h6>"+
                    "<div>"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
