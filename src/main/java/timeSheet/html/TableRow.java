package timeSheet.html;

import timeSheet.html.HTMLBase.BaseHTMLAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 8:38 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public class TableRow extends BaseHTMLAttributes {
    @XmlElement(name = "td")
    private List<TableData> dataList;

    @XmlElement(name = "th")
    private List<TableData> headerList;

    public List<TableData> getDataList() {
        return dataList;
    }

    public void setDataList(List<TableData> dataList) {
        this.dataList = dataList;
    }

    public void addData(TableData data) {
        checkDataList();
        dataList.add(data);
    }

    private void checkDataList() {
        if (dataList == null) {
            dataList = new ArrayList<TableData>();
        }
    }

    public List<TableData> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<TableData> headerList) {
        this.headerList = headerList;
    }

    public void addHeader(TableData data) {
        checkHeaderList();
        headerList.add(data);
    }

    private void checkHeaderList() {
        if (headerList == null) {
            headerList = new ArrayList<TableData>();
        }
    }
}
