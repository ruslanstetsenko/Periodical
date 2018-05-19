package dao;

import beans.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    public void create(Account account, Connection connection) throws SQLException;
    public Account read(int id, Connection connection);
    public void update(Account account, Connection connection) throws SQLException;
    public void delete(Account account, Connection connection) throws SQLException;
    public List<Account> getAll(Connection connection) throws SQLException;

}
