package connection;

import commands.CancelCreatePublicationCommand;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(CancelCreatePublicationCommand.class);

    private final static String DB_USERNAME = "username";
    private final static String DB_PASSWORD = "password";
    private final static String DB_URL = "url";
    private final static String DB_DRIVER_CLASS = "driver.class.name";

//    private final static String USE_UNICODE = "driver.class.name";
//    private final static String ENCODIND = "driver.class.name";

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

            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(1000);

        } catch (IOException e) {
            logger.error("Cant load connection pool", e);
        }
    }

    public static Connection getConnection(boolean autocommit) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(autocommit);
            return connection;
        } catch (SQLException e) {
            logger.error("Cant get connection from pool", e.getCause());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cant close connection", e.getCause());
            }
        }
    }

    //
    public static void transactionRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Cant rollback transaction", e.getCause());
            }
        }
    }
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            Context initialContext = new InitialContext();
//            Context envContext = (Context) initialContext.lookup("java:comp/env");
//            DataSource dataSource = (DataSource) envContext.lookup("jdbc/periodical");
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
//        } catch (NamingException e) {
//            System.out.println("Exception!!!!!!");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

}
