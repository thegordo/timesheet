package timeSheet.html;

import timeSheet.html.HTMLBase.BaseHTMLAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import java.util.ArrayList;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 8:59 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public class TableData extends BaseHTMLAttributes {
    @XmlMixed
    @XmlAnyElement
    private List<Object> valueList;

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }

    public void addValue(Object data) {
        checkValueList();
        valueList.add(data);
    }

    private void checkValueList() {
        if (valueList == null) {
            valueList = new ArrayList<Object>();
        }
    }
}
