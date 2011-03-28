package timeSheet.html;

import timeSheet.html.HTMLBase.BaseHTMLAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 8:35 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "table")
public class Table extends BaseHTMLAttributes {
    @XmlElement(name = "tr")
    private List<TableRow> rowList;

    @XmlElement(name = "thead")
    private TableHeader header;

    public List<TableRow> getRowList() {
        return rowList;
    }

    public void setRowList(List<TableRow> rowList) {
        this.rowList = rowList;
    }

    public void addRow(TableRow row) {
        checkRowList();
        rowList.add(row);
    }

    private void checkRowList() {
        if (rowList == null) {
            rowList = new ArrayList<TableRow>();
        }
    }

    public TableHeader getHeader() {
        return header;
    }

    public void setHeader(TableHeader header) {
        this.header = header;
    }
}
