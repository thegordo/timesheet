package timeSheet.html;

import timeSheet.html.HTMLBase.BaseHTMLAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 2:15 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Input extends BaseHTMLAttributes {
    @XmlAttribute
    private HTMLInputType type;
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String value;

    public Input() {
    }

    public Input(HTMLInputType type, String id, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Input(HTMLInputType type, String id, String name, String value) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HTMLInputType getType() {
        return type;
    }

    public void setType(HTMLInputType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
