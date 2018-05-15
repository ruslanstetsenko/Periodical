package service;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.UserDao;
import beens.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private UserDao userDao = DaoFactory.getUserDao();

    public void createUser(User user) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            userDao.create(user, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
