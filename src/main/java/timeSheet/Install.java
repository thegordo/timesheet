package timeSheet;

import timeSheet.database.DBType;
import timeSheet.database.entity.Employee;
import timeSheet.database.entity.EmployeeGroup;
import timeSheet.database.entity.GroupRole;
import timeSheet.database.manager.DatabaseManager;
import timeSheet.util.LoginType;
import timeSheet.util.PaySystemProperties;
import timeSheet.util.PropertyName;

import java.util.Calendar;

/**
 * User: John Lawrence
 * Date: 1/1/11
 * Time: 12:25 AM
 */
public class Install {
    private String companyName;
    private String dbLocation;
    private String dbUserName;
    private String dbPassword;
    private DBType dbType;
    private String LDAPServer;
    private String LDAPDomain;
    private String adminUserName;
    private String adminPassword;
    private String adminName;
    private DatabaseManager manager;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDbLocation() {
        return dbLocation;
    }

    public void setDbLocation(String dbLocation) {
        this.dbLocation = dbLocation;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    public String getLDAPServer() {
        return LDAPServer;
    }

    public void setLDAPServer(String LDAPServer) {
        this.LDAPServer = LDAPServer;
    }

    public String getLDAPDomain() {
        return LDAPDomain;
    }

    public void setLDAPDomain(String LDAPDomain) {
        this.LDAPDomain = LDAPDomain;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void doInstall() {
        storeProperties();
        setupDatabase();
        createInitialUser();
        setupLoginStuff();
    }

    private void storeProperties() {
        PaySystemProperties.setProperty(PropertyName.COMPANY_NAME, companyName);
    }

    private void setupDatabase() {
        PaySystemProperties.setProperty(PropertyName.DB_TYPE, dbType.toString());
        PaySystemProperties.setProperty(PropertyName.DB_LOCATION, dbLocation);
        PaySystemProperties.setProperty(PropertyName.DB_USER_NAME, dbUserName);
        PaySystemProperties.setProperty(PropertyName.DB_PASSWORD, dbPassword);


        manager = new DatabaseManager();
        manager.connect(true);
    }

    private void createInitialUser() {
        EmployeeGroup adminGroup = new EmployeeGroup();
        adminGroup.setName("admin");

        Employee emp = new Employee();
        emp.setUserName(adminUserName);
        emp.setName(adminName);
        emp.setActiveFlag(true);
        emp.setHireDate(Calendar.getInstance().getTime());
        emp.setEmailAddress("");
        emp.setPassword(adminPassword);
        emp.setPtoAllowed(false);
        emp.setGroup(adminGroup);
        emp.setSalary(true);
        emp.setFullTimeDate(Calendar.getInstance().getTime());
        emp.setRole(GroupRole.Administrator);

        adminGroup.addEmployee(emp);

        manager.persist(emp);
    }

    private void setupLoginStuff() {
        if (LDAPDomain != null && LDAPServer != null) {
            PaySystemProperties.setProperty(PropertyName.LOGIN_TYPE, LoginType.LDAP.toString());
            PaySystemProperties.setProperty(PropertyName.LDAP_SERVER, LDAPServer);
            PaySystemProperties.setProperty(PropertyName.LDAP_DOMAIN, LDAPDomain);
        }
        PaySystemProperties.setProperty(PropertyName.LOGIN_TYPE, LoginType.Database.toString());
    }
}
