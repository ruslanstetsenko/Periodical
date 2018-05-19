package service;

import connection.ConnectionPool;
import dao.AccountDao;
import dao.DaoFactory;
import beans.Account;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao = DaoFactory.getAccountDao();

    public void create(Account account, Connection connection) {
        try {
            accountDao.create(account, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account read(int id, Connection connection) {
        Account account = new Account();
        account = accountDao.read(id, connection);
        ConnectionPool.closeConnection(connection);
        return account;
    }

    public void update(Account account, Connection connection) {
        try {
            accountDao.update(account, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
