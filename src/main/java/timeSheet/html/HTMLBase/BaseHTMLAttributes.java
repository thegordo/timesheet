package timeSheet.html.HTMLBase;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 8:51 AM
 */
@XmlTransient
public abstract class BaseHTMLAttributes {
    @XmlAttribute(name = "class")
    private String htmlClass;

    public String getHtmlClass() {
        return htmlClass;
    }

    public void setHtmlClass(String htmlClass) {
        this.htmlClass = htmlClass;
    }
}
