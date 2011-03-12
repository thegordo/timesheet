package timeSheet.database.manager;

import timeSheet.database.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: John Lawrence
 * Date: 12/25/10
 * Time: 10:29 PM
 */
public class EmployeeManager {
    private DatabaseManager manager;
    private EntityManager em;

    public EmployeeManager() {
        manager = new DatabaseManager();
        em = manager.getEntityManager();
    }

    public List<Employee> getEmployeeList() {
        return em.createQuery("Select c from Employee c", Employee.class).getResultList();
    }

    public Employee getEmployee(String userName) {
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where upper(c.userName) = upper(:userName)", Employee.class);
        query.setParameter("userName", userName);
        return manager.getSingleResult(query);
    }

    public Employee getEmployee(int id) {
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where c.id = :id", Employee.class);
        query.setParameter("id", id);
        return manager.getSingleResult(query);
    }

    public void deleteEmployee(int id) {
        manager.delete(getEmployee(id));
    }

    public Employee saveEmployee(Employee employee) {
        return manager.persist(employee);
    }

    public String getEmployeeOptions(Employee sessionEmployee, Employee chosen) {
        List<Employee> employeeList;
        if (sessionEmployee.isAdmin()) {
            employeeList = getAllActiveHourlyEmployees();
        } else {
            employeeList = getEmployeesForGroupNonSalaried(sessionEmployee);
        }
        StringBuilder options = new StringBuilder();
        for(Employee employee : employeeList){
            if (employee.equals(chosen)) {
                options.append("<option value='").append(employee.getId()).append("' selected>").append(employee.getName()).append("</option>");
            } else {
                options.append("<option value='").append(employee.getId()).append("'>").append(employee.getName()).append("</option>");
            }
        }
        return options.toString();
    }

    private List<Employee> getEmployeesForGroupNonSalaried(Employee sessionEmployee) {
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where c.group = :group and c.isSalary = false", Employee.class);
        query.setParameter("group", sessionEmployee.getGroup());
        return query.getResultList();
    }

    private List<Employee> getAllActiveHourlyEmployees() {
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where c.isSalary = false", Employee.class);
        return query.getResultList();
    }
}
