package timeSheet.html;

import timeSheet.html.HTMLBase.BaseHTMLAttributes;

import javax.xml.bind.annotation.*;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 2:11 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "label")
public class Label extends BaseHTMLAttributes {
    @XmlAttribute(name = "for")
    private String forAttribute;

    @XmlValue
    private String name;

    public Label() {
    }

    public Label(String labelFor, String name) {
        forAttribute = labelFor;
        this.name = name;
    }

    public String getForAttribute() {
        return forAttribute;
    }

    public void setForAttribute(String forAttribute) {
        this.forAttribute = forAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
