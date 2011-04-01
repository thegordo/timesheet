package timeSheet;

import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: John Lawrence
 * Date: 3/27/11
 * Time: 8:17 AM
 */
public class EnvironmentSetup implements ServletContextListener {
    private Logger logger = Logger.getLogger(EnvironmentSetup.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            logger.info("Starting the H2 server!");
            Server.createTcpServer().start();
            logger.info("H2 server started.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "H2 already running.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Attempting to stop the H2 server!");
        try {
            Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        logger.info("H2 server shutdown.");
    }
}
