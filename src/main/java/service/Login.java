package service;

import connection.ConnectionPool;
import dao.AccountDao;
import dao.DaoFactory;
import dao.UserDao;
import beens.Account;
import beens.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Login {

    public int enterInAccount(String login, String password) {
        int userRole = 1;
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
                                new AdminWindows().loadAdminWindow(connection);
                            } else {
                                userRole = 2;
                                new UserWindows().loadUserWindow(user, connection);
                            }
                            break;
                        }
                    }
                    break;
                }
                //wrong login or password
                userRole = 3;
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
        return userRole;
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
