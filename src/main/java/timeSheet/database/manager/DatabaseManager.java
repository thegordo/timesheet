package timeSheet.database.manager;

import timeSheet.database.entity.BaseObject;
import timeSheet.database.entity.Employee;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;


/**
 * User: John Lawrence
 * Date: 12/10/10
 * Time: 1:03 AM
 */
public class DatabaseManager {
    private String TCP_CONNECTION_STRING = "jdbc:h2:tcp://localhost";
    private String EMBEDDED_CONNECTION_STRING = "jdbc:h2:";
    private EntityManager em;
    private boolean isConnected;

    public DatabaseManager() {
    }

    public void connect(boolean create) {
        HashMap<String, String> properties = getProperties(create);

        // TODO: Set this up so that the user can change databases and database urls
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PaySystem", properties);
        em = factory.createEntityManager();
        isConnected = true;
    }

    public boolean testConnection() {
        HashMap<String, String> properties = getProperties(false);
        try {
            Class.forName(properties.get(JDBC_DRIVER));
            Connection conn = DriverManager.getConnection(properties.get(JDBC_URL));
            conn.close(); // Database exists.
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Employee getEmployee(String userName) {
        ensureConnection();
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where upper(c.userName) = upper(:userName)", Employee.class);
        query.setParameter("userName", userName);
        try {
            return query.getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Employee getEmployee(int id) {
        ensureConnection();
        TypedQuery<Employee> query = em.createQuery("Select c from Employee c where c.id = :id", Employee.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    private void ensureConnection() {
        if (!isConnected) {
            connect(false);
        }
    }

    private HashMap<String, String> getProperties(boolean create) {
        HashMap<String, String> properties = new HashMap<String, String>();
        if (create) {
            properties.put(JDBC_URL, TCP_CONNECTION_STRING + "/~/timeSheet");
            properties.put(DDL_GENERATION, CREATE_ONLY);
            properties.put(DDL_GENERATION_MODE, DDL_BOTH_GENERATION);
        } else {
            properties.put(JDBC_URL, TCP_CONNECTION_STRING + "/~/timeSheet;IFEXISTS=TRUE");
        }
        properties.put(JDBC_USER, "");
        properties.put(JDBC_PASSWORD, "");
        properties.put(JDBC_DRIVER, "org.h2.Driver");
        return properties;
    }

    public <T extends BaseObject> T persist(T object) {
        ensureConnection();
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

    public List<Employee> getEmployeeList() {
        ensureConnection();
        return em.createQuery("Select c from Employee c", Employee.class).getResultList();
    }

    public EntityManager getEntityManager() {
        ensureConnection();
        return em;
    }

    public <T> T getSingleResult(TypedQuery<T> query) {
        List<T> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void delete(Object object) {
        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            Logger.getLogger("Manager").log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }
}
