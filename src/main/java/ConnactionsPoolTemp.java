import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnactionsPoolTemp {

    public static void main(String[] args) {
        Connection connection = ConnectionPool.getConnection();

        try {
            System.out.println(connection.getAutoCommit());
            System.out.println(connection.isClosed());
            connection.close();
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    private static String url = "jdbc:mysql://localhost:3306/periodical";
//    private static String login    = "root";
//    private static String password = "root";
//    private static Connection connection;

//    private static void connectPool() {
//        InitialContext initContext= null;
//        DataSource ds = null;
//
//        try {
//            initContext = new InitialContext();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/periodical");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Connection conn = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    private static void connectDriverManager() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Драйвер найден! Попытка установки соеденения...");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Драйвер не подключен!");
//        }
//
//        try {
//            connection = DriverManager.getConnection(url, login, password);
//
//        } catch (SQLException e) {
//            System.out.println("Соеденение не установлено!");
//        } finally {
//            if (connection != null) {
//                System.out.println("Соеденение установлено!");
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    private static Connection getConnection(String contextFileName) {
//
//    }
}
