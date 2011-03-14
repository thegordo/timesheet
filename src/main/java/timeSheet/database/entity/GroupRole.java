package timeSheet.database.entity;

/**
 * User: John Lawrence
 * Date: 12/13/10
 * Time: 1:01 AM
 */
public enum GroupRole {
    Administrator("Administrator"),
    Executive("Executive"),
    Manager("Manager"),
    AssistantManager("Assistant Manager"),
    TimeSheetApproval("Time Sheet Approver"),
    Employee("Regular Employee"),
    ;
    private String displayString;

    GroupRole(String displayString) {
        this.displayString = displayString;
    }

    public String toDisplayString() {
        return displayString;
    }

    public static String getHtmlOptions(GroupRole role) {
        StringBuilder roleOptions = new StringBuilder();
        for (GroupRole groupRole : values()) {
            if (groupRole == role) {
                roleOptions.append("<option selected=\"selected\" value=\"").append(groupRole.toString()).append("\">").append(groupRole.toDisplayString()).append("</option>\n");
            } else {
                roleOptions.append("<option value=\"").append(groupRole.toString()).append("\">").append(groupRole.toDisplayString()).append("</option>\n");
            }
        }
        return roleOptions.toString();
    }

    public boolean isTimeManager() {
        return this != Employee;
    }
}
