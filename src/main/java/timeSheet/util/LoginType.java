package timeSheet.util;

import timeSheet.database.manager.SettingsManager;

/**
 * User: John Lawrence
 * Date: 1/1/11
 * Time: 1:58 PM
 */
public enum LoginType {
    Database("Database"),
    LDAP("LDAP"),
    ;

    private String displayName;

    LoginType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getSelection() {
        SettingsManager manager = new SettingsManager();

        LoginType selectedType = manager.getLoginType();
        StringBuilder options = new StringBuilder();
        for (LoginType type : values()) {
            options.append("<option value=\"").append(type.toString()).append("\" ");
            if (selectedType == type) {
                options.append("selected=\"selected\"");
            }
            options.append(">").append(type.getDisplayName()).append("</option>\n");
        }
        return options.toString();
    }
}
