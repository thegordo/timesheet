package timeSheet.util;

/**
 * User: John Lawrence
 * Date: 12/25/10
 * Time: 11:59 PM
 */
public enum PropertyName {
    COMPANY_NAME("companyName"),
    COMPANY_CODE("companyCode"),
    LOGIN_TYPE("loginType"),
    LDAP_SERVER("ldapServer"),
    LDAP_DOMAIN("ldapDomain"),
    DB_TYPE("dbType"),
    DB_LOCATION("dbLocation"),
    DB_USER_NAME("dbUserName"),
    DB_PASSWORD("dbPassword"),
    ;
    private String name;

    PropertyName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
