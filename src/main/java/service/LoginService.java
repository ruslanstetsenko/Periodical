package service;

import connection.ConnectionPool;
import dao.interfaces.AccountDao;
import dao.DaoFactory;
import dao.interfaces.UserDao;
import beans.Account;
import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * Login service. Checking the authorization options
 * @author Stetsenko Ruslan
 */
public class LoginService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

    private AccountDao accountDao = DaoFactory.getAccountDao();
    private UserDao userDao = DaoFactory.getUserDao();

/**
 * Check account in database
 * @param login user login
 * @param password user password
 * @return account if the search is successful or null
 * @exception DataBaseWorkException errors in DAO layer
 */
    public Account checkAccount(String login, String password) {
        Connection connection = ConnectionPool.getConnection(true);
        try {
            List<Account> accountList = accountDao.getAll(connection);
            for (Account account : accountList) {
                if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                    return account;
                }
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't check account", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }

    /**
     * Check login
     * @param login user login
     * @return true if the user name exists in the database
     * @exception DataBaseWorkException errors in DAO layer
     */
    public boolean checkLogin(String login) {
        Connection connection = ConnectionPool.getConnection(true);
        try {
            List<Account> accountList = accountDao.getAll(connection);
            for (Account account : accountList) {
                if (login.equals(account.getLogin())) {
                    return true;
                }
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't check login", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return false;
    }

    /**
     * Get user from database
     * @param account
     * @return user if the search is successful or null
     * @exception DataBaseWorkException errors in DAO layer
     */
    public User getUser(Account account) {
        Connection connection = ConnectionPool.getConnection(true);
        try {
            List<User> userList = userDao.getAll(connection);
            for (User user : userList) {
                if (account.getId() == user.getAccountsId()) {
                    return user;
                }
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get user", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }
}
