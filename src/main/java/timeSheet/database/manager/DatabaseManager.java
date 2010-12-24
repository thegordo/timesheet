package timeSheet.database.manager;

import timeSheet.database.entity.BaseObject;
import timeSheet.database.entity.Employee;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.openjpa.persistence.JPAProperties.*;

/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:03 AM
 */
public class DatabaseManager {
    private EntityManager em;

    public DatabaseManager() {
    }

    public void connect(boolean create) {
        HashMap<String, String> properties = getProperties(create);

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
        if (create) {
            properties.put(JDBC_URL, "jdbc:h2:tcp://localhost/~/timeSheet");
        } else {
            properties.put(JDBC_URL, "jdbc:h2:tcp://localhost/~/timeSheet;IFEXISTS=TRUE");
        }
        properties.put(JDBC_USER, "");
        properties.put(JDBC_PASSWORD, "");
        properties.put(JDBC_DRIVER, "org.h2.Driver");
        return properties;
    }

    public <T extends BaseObject> T persist(T object) {
        T returnVal = null;
        try {
            em.getTransaction().begin();
            returnVal = em.merge(object);
            em.persist(returnVal);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            Logger.getLogger("Manager").log(Level.SEVERE, e.getMessage(), e);
        }
        return returnVal;
    }
}
