package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.entity.GroupRole;
import timeSheet.database.manager.EmployeeManager;
import timeSheet.database.manager.GroupManager;
import timeSheet.database.manager.SettingsManager;
import timeSheet.util.TimeSheetException;
import timeSheet.util.ldap.LDAPAuthenticate;

import java.util.Date;

/**
 * User: John Lawrence
 * Date: 1/20/11
 * Time: 5:35 AM
 */
public class Login {
    private Employee employee;
    private String password;
    private LDAPAuthenticate ldap;
    private String failureMessage;
    private String name;

    public Login(String name, String password) {
        this.password = password;
        this.name = name;
        EmployeeManager manager = new EmployeeManager();
        employee = manager.getEmployee(name);
        SettingsManager settings = new SettingsManager();
        ldap = new LDAPAuthenticate(settings.getLDAPServer(), settings.getLDAPDomain());
    }

    public boolean checkDatabaseLogin() {
        return employee != null && employee.getPassword().equals(password);
    }

    public boolean checkLDAPLogin(String password) {
        try {
            ldap.login(name, password);
            if (employee == null) {
                createEmployee(name, password);
            }
            return true;
        } catch (TimeSheetException e) {
            failureMessage = e.getMessage();
            return false;
        }
    }

    private void createEmployee(String name, String password) {
        Employee employee = new Employee();
        employee.setHireDate(new Date());
        employee.setFullTimeDate(null);
        employee.setActiveFlag(true);
        GroupManager manager = new GroupManager();
        employee.setGroup(manager.getGroup(1));
        employee.setEmailAddress("");
        employee.setFileNumber("");
        employee.setName(name);
        employee.setUserName(name);
        employee.setRole(GroupRole.Employee);
        employee.setSalary(false);
        employee.setPtoAllowed(false);
        employee.setPassword(password);
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getFailureMessage() {
        return failureMessage;
    }
}
