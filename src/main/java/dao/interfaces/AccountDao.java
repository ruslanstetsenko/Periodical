package dao.interfaces;

import beans.Account;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.util.List;

public interface AccountDao {

    int create(String login, String password, Connection connection) throws DataBaseWorkException;
    Account read(int id, Connection connection) throws DataBaseWorkException;
    void update(int id, String login, String password, Connection connection) throws DataBaseWorkException;
    void delete(Account account, Connection connection) throws DataBaseWorkException;
    List<Account> getAll(Connection connection) throws DataBaseWorkException;
//    int getLastId(Connection connection) throws DataBaseWorkException;
}
