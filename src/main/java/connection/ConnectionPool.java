package connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionPool {
    private final static String DB_USERNAME = "username";
    private final static String DB_PASSWORD = "password";
    private final static String DB_URL = "url";
    private final static String DB_DRIVER_CLASS = "driver.class.name";

    private static Properties properties;
    private static BasicDataSource dataSource;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/database.properties"));

            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
            dataSource.setUrl(properties.getProperty(DB_URL));
            dataSource.setUsername(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));

            dataSource.setMinIdle(100);
            dataSource.setMaxIdle(1000);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
