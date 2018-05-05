package service;

import dao.AccountDao;
import dao.DaoFactory;
import entity.Account;

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
        try {
            account = accountDao.read(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
