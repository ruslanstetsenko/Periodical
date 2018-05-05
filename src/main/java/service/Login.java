package service;

import connection.ConnectionPool;
import dao.AccountDao;
import dao.DaoFactory;
import dao.UserDao;
import entity.Account;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Login {

    public void enterInAccount(String login, String password) {
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();
        Connection connection = ConnectionPool.getConnection();
        User user;
        int accountId;

        try {
            List<Account> accountList = accountDao.getAll(connection);
            for (Account account : accountList) {
                if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                    List<User> userList = userDao.getAll(connection);
                    accountId = account.getId();
                    for (User user1 : userList) {
                        if (accountId == user1.getAccountsId()) {
                            user = user1;
                            if (user.getUserRoleId() == 1) {
                                new UserWindows().loadAdminWindow(user, connection);
                            } else {
                                new UserWindows().loadUserWindow(connection);
                            }
                            break;
                        }
                    }
                    break;
                }
                    //wrong login or password
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//
//    private void loadAdminWindow(Connection connection) throws SQLException {
//        PublicationDao publicationDao = DaoFactory.getPublicationDao();
//
//        List<Publication> publicationList = publicationDao.getAll(connection);
//
//    }
//
//    private void loadUserWindow(Connection connection) {
//
//    }

}
