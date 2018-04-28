package dao;

import entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static final int LOGIN = 1;
    private static final int PASSWORD = 2;

    @Override
    public void create(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;

        preparedStatement = connection.prepareStatement("INSERT INTO accounts (login, password) VALUES (?, ?)");
        preparedStatement.setString(LOGIN, account.getLogin());
        preparedStatement.setString(PASSWORD, account.getPassword());
        preparedStatement.execute();
    }

    @Override
    public Account read(int id, Connection connection) throws SQLException {
        Account account = new Account();

        PreparedStatement preparedStatement = connection.prepareStatement("select login, password from accounts where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        account.setId(id);
        account.setLogin(resultSet.getString(LOGIN));
        account.setPassword(resultSet.getString(PASSWORD));

        return account;
    }

    @Override
    public void update(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET login=?, password=? WHERE id=?");
        preparedStatement.setString(LOGIN, account.getLogin());
        preparedStatement.setString(PASSWORD, account.getPassword());
        preparedStatement.setInt(3, account.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM accounts WHERE id=?");
        preparedStatement.setInt(1, account.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Account> getAll(Connection connection) throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts ORDER BY id");

        while (resultSet.next()) {
            list.add(new Account.AccountBuilder().setId(resultSet.getInt("id"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password")).built());

        }
        return list;
    }
}

