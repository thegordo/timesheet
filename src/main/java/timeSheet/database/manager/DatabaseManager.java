package timeSheet.database.manager;

import timeSheet.database.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:03 AM
 */
public class DatabaseManager {
    private EntityManager em;

    public DatabaseManager() {
    }

    public void connect() {
        HashMap<String, String> properties = getProperties(true);

        // TODO: Set this up so that the user can change databases and database urls
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PaySystem", properties);
        em = factory.createEntityManager();
    }

    public boolean testConnection() {
        HashMap<String, String> properties = getProperties(true);
        try {
            Class.forName(properties.get(JDBC_DRIVER));
            Connection conn = DriverManager.getConnection(properties.get(JDBC_URL) + ";IFEXISTS=TRUE");
            conn.close(); // Database exists.
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Employee getEmployee(String userName) {
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where upper(c.userName) = upper(:userName)", Employee.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();
    }

    private HashMap<String, String> getProperties(boolean create) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put(JDBC_URL, "jdbc:h2:tcp://localhost/~/timeSheet");
        properties.put(JDBC_USER, "");
        properties.put(JDBC_PASSWORD, "");
        properties.put(JDBC_DRIVER, "org.h2.Driver");
        if (create) {
            properties.put(DDL_GENERATION, CREATE_ONLY);
            properties.put(DDL_GENERATION_MODE, DDL_BOTH_GENERATION);
        }
        return properties;
    }
}
