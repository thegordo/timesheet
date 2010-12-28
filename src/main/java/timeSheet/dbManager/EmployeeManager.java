package timeSheet.dbManager;

import timeSheet.database.entity.Employee;
import timeSheet.database.manager.DatabaseManager;

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
}
