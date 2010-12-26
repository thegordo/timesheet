package timeSheet;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.DatabaseManager;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 12/25/10
 * Time: 10:29 PM
 */
public class EmployeeManager {
    private HttpSession session;

    public EmployeeManager(HttpSession session) {
        this.session = session;
    }

    public List<Employee> getEmployeeList() {
        DatabaseManager manager = new DatabaseManager();
        return manager.getEmployeeList();
    }
}
