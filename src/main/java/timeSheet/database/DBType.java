package timeSheet.database;

import timeSheet.util.PaySystemProperties;
import timeSheet.util.PropertyName;

/**
 * User: John Lawrence
 * Date: 1/1/11
 * Time: 1:49 PM
 */
public enum DBType {
    H2("H2"),
    H2Embedded("H2 Embedded"),
    MySQL("MySql"),
    ;

    private String displayName;

    private DBType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getSelection() {
        DBType selectedType = DBType.valueOf(PaySystemProperties.getProperty(PropertyName.DB_TYPE));
        StringBuilder options = new StringBuilder();
        for (DBType type : values()) {
            options.append("<option value=\"").append(type.toString()).append("\" ");
            if (selectedType == type) {
                options.append("selected=\"selected\"");
            }
            options.append(">").append(type.getDisplayName()).append("</option>\n");
        }
        return options.toString();
    }
}
