package timeSheet.form;

import timeSheet.Login;
import timeSheet.SessionConst;
import timeSheet.database.manager.SettingsManager;
import timeSheet.util.LoginType;
import timeSheet.util.SHA;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * User: John Lawrence
 * Date: 3/14/11
 * Time: 9:57 PM
 */
public class Credentials {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String process(HttpSession session) {
        String pass = new SHA(password).toString();
        Logger.getLogger("login").info("Attempting to login user " + userName);
        if (userName == null || pass == null) {
            return "Please re-enter the user name and password properly";
        } else {
            Login login = new Login(userName, pass);
            SettingsManager settings = new SettingsManager();
            boolean authorized;
            if (settings.getLoginType() == LoginType.LDAP) {
                authorized = login.checkLDAPLogin(password);
            } else {
                authorized = login.checkDatabaseLogin();
            }
            if (authorized) {
                session.setAttribute(SessionConst.employee.toString(), login.getEmployee());
                return null;
            } else {
                return "Invalid username and pass combination";
            }
        }
    }
}
