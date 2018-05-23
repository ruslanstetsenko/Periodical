package dao.interfaces;

import beans.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    void create(String login, String password, Connection connection);
    Account read(int id, Connection connection);
    void update(int id, String login, String password, Connection connection);
    void delete(Account account, Connection connection);
    List<Account> getAll(Connection connection);
    int getLastId(Connection connection);
}
