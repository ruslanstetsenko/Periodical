package dao;

import beens.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    @Override
    public void create(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accounts (login, password) VALUES (?, ?)");
        preparedStatement.setString(1, account.getLogin());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.execute();
    }

    @Override
    public Account read(int id, Connection connection) throws SQLException {
        Account account = new Account();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, password FROM accounts WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        account.setId(id);
        account.setLogin(resultSet.getString("login"));
        account.setPassword(resultSet.getString("password"));

        return account;
    }

    @Override
    public void update(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET login=?, password=? WHERE id=?");
        preparedStatement.setString(1, account.getLogin());
        preparedStatement.setString(2, account.getPassword());
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
            list.add(new Account.Builder()
                    .setId(resultSet.getInt("id"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .build());
        }
        return list;
    }
}

