package timeSheet.html;

/**
 * User: John Lawrence
 * Date: 3/27/11
 * Time: 7:30 AM
 */
public class UtilHtml {
    public static TableData getSimpleTableData(String name) {
        TableData data = getTableData();
        data.addValue(name);
        return data;
    }

    public static TableData getSimpleTableInputData(String idName, HTMLInputType type) {
        return getSimpleTableInputData(idName, type, null);
    }

    public static TableData getSimpleTableInputData(String idName, HTMLInputType type, String value) {
        TableData data = getTableData();
        data.addValue(new Label(idName, ""));
        if (value == null) {
            data.addValue(new Input(type, idName, idName));
        } else {
            data.addValue(new Input(type, idName, idName, value));
        }
        return data;
    }

    public static TableData getTableData() {
        TableData data = new TableData();
        data.setHtmlClass("hourTable");
        return data;
    }
}
