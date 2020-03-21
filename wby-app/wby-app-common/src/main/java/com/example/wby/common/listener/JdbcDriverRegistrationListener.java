package com.example.wby.common.listener;

import org.slf4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


public class JdbcDriverRegistrationListener implements ServletContextListener {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(JdbcDriverRegistrationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    logger.info("de-registering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    logger.error("Error de-registering JDBC driver {}", driver, ex);
                }
            } else {
                logger.trace("Not de-registering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
            }
        }

    }
}
