package timeSheet.util;

/**
 * User: John Lawrence
 * Date: 12/25/10
 * Time: 11:59 PM
 */
public enum PropertyName {
    COMPANY_NAME("companyName"),
    ;
    private String name;

    PropertyName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
