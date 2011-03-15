package timeSheet.database.manager;

import timeSheet.database.DBType;
import timeSheet.database.entity.BaseObject;
import timeSheet.util.PaySystemProperties;
import timeSheet.util.PropertyName;

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
    private static final String H2_TCP_CONNECTION_STRING = "jdbc:h2:tcp://localhost";
    private static final String H2_EMBEDDED_CONNECTION_STRING = "jdbc:h2:";
    private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://";
    private EntityManager em;
    private boolean isConnected;

    public DatabaseManager() {
    }

    public void connect(boolean create) {
        HashMap<String, String> properties = getProperties(create);
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

    private void ensureConnection() {
        if (!isConnected) {
            connect(false);
        }
    }

    private HashMap<String, String> getProperties(boolean create) {
        String location = PaySystemProperties.getProperty(PropertyName.DB_LOCATION);
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put(JDBC_USER, PaySystemProperties.getProperty(PropertyName.DB_USER_NAME, ""));
        properties.put(JDBC_PASSWORD, PaySystemProperties.getProperty(PropertyName.DB_PASSWORD, ""));
        if (create) {
            properties.put(DDL_GENERATION, CREATE_ONLY);

        }
        if (PaySystemProperties.getProperty(PropertyName.DB_TYPE) == null) {
            return null;
        }
        switch (DBType.valueOf(PaySystemProperties.getProperty(PropertyName.DB_TYPE))) {
            case H2:
                properties.put(JDBC_URL, H2_TCP_CONNECTION_STRING + location + (create ? "" : ";IFEXISTS=TRUE"));
                properties.put(JDBC_DRIVER, "org.h2.Driver");
                break;
            case H2Embedded:
                properties.put(JDBC_URL, H2_EMBEDDED_CONNECTION_STRING + location + (create ? "" : ";IFEXISTS=TRUE"));
                properties.put(JDBC_DRIVER, "org.h2.Driver");
                break;
            case MySQL:
                properties.put(JDBC_URL, MYSQL_CONNECTION_STRING + location);
                properties.put(JDBC_DRIVER, "com.mysql.jdbc.Driver");
                break;
        }
        return properties;
    }

    public <T extends BaseObject> T persist(T object) {
        ensureConnection();
        if (object.getId() < 1) {
            return persistNew(object);
        } else {
            return merge(object);
        }
    }

    private <T extends BaseObject> T persistNew(T object) {
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

    public <T extends BaseObject> T merge(T object) {
        ensureConnection();
        try {
            em.getTransaction().begin();
            object = em.merge(object);
            em.persist(object);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            Logger.getLogger("Manager").log(Level.SEVERE, e.getMessage(), e);
        }
        return object;
    }

    public EntityManager getEntityManager() {
        ensureConnection();
        return em;
    }

    public <T> T getSingleResult(TypedQuery<T> query) {
        ensureConnection();
        List<T> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void delete(Object object) {
        ensureConnection();
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
// sadie
