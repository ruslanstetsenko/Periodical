package service;

import connection.ConnectionPool;
import dao.interfaces.AccountDao;
import dao.DaoFactory;
import dao.interfaces.UserDao;
import beans.Account;
import beans.User;

import java.sql.Connection;
import java.util.List;

public class LoginService {

    private AccountDao accountDao = DaoFactory.getAccountDao();
    private UserDao userDao = DaoFactory.getUserDao();

    public Account checkAccount(String login, String password) {
        Connection connection = ConnectionPool.getConnection(true);
//        boolean loginCheck = false;
//        UserDao userDao = DaoFactory.getUserDao();
//        User user = new User();
//        int accountId;
        List<Account> accountList = accountDao.getAll(connection);
        for (Account account : accountList) {
            if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                return account;
            }
        }
        ConnectionPool.closeConnection(connection);
        return null;
    }

    public User getUser(Account account) {
        Connection connection = ConnectionPool.getConnection(true);
        List<User> userList = userDao.getAll(connection);
        for (User user : userList) {
            if (account.getId() == user.getAccountsId()) {
                return user;
            }
        }
        ConnectionPool.closeConnection(connection);
        return null;
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
