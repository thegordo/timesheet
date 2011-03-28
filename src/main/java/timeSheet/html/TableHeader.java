package timeSheet.html;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: John Lawrence
 * Date: 3/27/11
 * Time: 8:26 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public class TableHeader {
    @XmlElement
    private TableRow row;

    public TableRow getRow() {
        return row;
    }

    public void setRow(TableRow row) {
        this.row = row;
    }
}
