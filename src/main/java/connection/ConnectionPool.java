package connection;

import commands.CancelCreatePublicationCommand;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(CancelCreatePublicationCommand.class);

    private final static String DB_USERNAME = "username";
    private final static String DB_PASSWORD = "password";
    private final static String DB_URL = "url";
    private final static String DB_DRIVER_CLASS = "driver.class.name";
    private final static String MAX_IDLE = "maxIdle";
    private final static String MAX_WAIT = "maxWaitMillis";
    private final static String MAX_TOTAL = "maxTotal";
    private final static String TRANSACTION_ISOLATION = "transactionIsolation";

    private static Properties properties = new Properties();
    private static ClassLoader classLoader;
    private static InputStream inputStream;
    private static BasicDataSource dataSource;

    static {
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream("database.properties");
            properties.load(inputStream);
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
            dataSource.setUrl(properties.getProperty(DB_URL));
            dataSource.setUsername(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));
            dataSource.setMaxIdle(Integer.valueOf(properties.getProperty(MAX_IDLE)));
            dataSource.setMaxWaitMillis(Long.valueOf(properties.getProperty(MAX_WAIT)));
            dataSource.setMaxTotal(Integer.valueOf(properties.getProperty(MAX_TOTAL)));
            dataSource.setDefaultTransactionIsolation(Integer.valueOf(properties.getProperty(TRANSACTION_ISOLATION)));

        } catch (IOException e) {
            LOGGER.error("Cant load connection pool", e);
        }
    }

    public static Connection getConnection(boolean autocommit) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(autocommit);
            return connection;
        } catch (SQLException e) {
        }
        return connection;
    }

    public static void commitTransaction(Connection connection) {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
                LOGGER.info("CONNECTION CLOSED");
            } catch (SQLException e) {
            }
        }
    }

    public static void transactionRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e) {
            }
        }
    }
}
