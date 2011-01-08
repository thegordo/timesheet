package timeSheet.database.manager;

import timeSheet.database.DBType;
import timeSheet.util.LoginType;
import timeSheet.util.PaySystemProperties;
import timeSheet.util.PropertyName;

import java.util.Map;

/**
 * User: John Lawrence
 * Date: 1/7/11
 * Time: 10:54 PM
 *
 * Class for easing the code in the JSP
 */
public class SettingsManager {

    public String getLDAPServer() {
        String ldapServer = PaySystemProperties.getProperty(PropertyName.LDAP_SERVER);
        return (ldapServer != null) ? ldapServer : "";
    }

    public String getLDAPDomain() {
        String ldapDomain = PaySystemProperties.getProperty(PropertyName.LDAP_DOMAIN);
        return ldapDomain != null ? ldapDomain:"";
    }

    public String getDBLocation() {
        String dbLocation = PaySystemProperties.getProperty(PropertyName.DB_LOCATION);
        return (dbLocation != null) ? dbLocation : "";
    }

    public String getDBUserName() {
        String dbUserName = PaySystemProperties.getProperty(PropertyName.DB_USER_NAME);
        return (dbUserName != null) ? dbUserName : "";
    }

    public String getDBPassword() {
        String dbPassword = PaySystemProperties.getProperty(PropertyName.DB_PASSWORD);
        return (dbPassword != null) ? dbPassword : "";
    }

    public void saveParameters(Map<String, String[]> parameterMap) {
        if (parameterMap.isEmpty()) {
            return;
        }

        LoginType loginType = LoginType.valueOf(parameterMap.get(PropertyName.LOGIN_TYPE.getName())[0]);
        PaySystemProperties.setProperty(PropertyName.LOGIN_TYPE, loginType.toString());
        if (loginType == LoginType.LDAP) {
            PaySystemProperties.setProperty(PropertyName.LDAP_SERVER, parameterMap.get(PropertyName.LDAP_SERVER.getName())[0]);
            PaySystemProperties.setProperty(PropertyName.LDAP_DOMAIN, parameterMap.get(PropertyName.LDAP_DOMAIN.getName())[0]);
        } else {
            PaySystemProperties.setProperty(PropertyName.LDAP_SERVER, "");
            PaySystemProperties.setProperty(PropertyName.LDAP_DOMAIN, "");
        }

        DBType type = DBType.valueOf(parameterMap.get(PropertyName.DB_TYPE.getName())[0]);
        PaySystemProperties.setProperty(PropertyName.DB_TYPE, type.toString());
        PaySystemProperties.setProperty(PropertyName.DB_LOCATION, parameterMap.get(PropertyName.DB_LOCATION.getName())[0]);
        PaySystemProperties.setProperty(PropertyName.DB_USER_NAME, parameterMap.get(PropertyName.DB_USER_NAME.getName())[0]);
        PaySystemProperties.setProperty(PropertyName.DB_PASSWORD, parameterMap.get(PropertyName.DB_PASSWORD.getName())[0]);
    }
}
