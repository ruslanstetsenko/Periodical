package dao.implementations;

import beans.Account;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.AccountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = LogManager.getLogger(AccountDaoImpl.class);

    @Override
    public void create(String login, String password, Connection connection) {
        String sql = "INSERT INTO accounts (login, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create user account in DB", e.getCause());
            e.printStackTrace();
        }
    }

    @Override
    public Account read(int id, Connection connection) {
        Account account = new Account();
        String sql = "SELECT login, password FROM accounts WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setId(id);
                account.setLogin(resultSet.getString("login"));
                account.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            logger.error("Can't read user account from DB", e.getCause());
        }
        return account;
    }

    @Override
    public void update(int id, String login, String password, Connection connection) {
        String sql = "UPDATE accounts SET login=?, password=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user account in DB", e.getCause());
        }
    }

    @Override
    public void delete(Account account, Connection connection) {
        String sql = "DELETE FROM accounts WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user account in DB", e.getCause());
        }
    }

    @Override
    public List<Account> getAll(Connection connection) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts ORDER BY id";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Account.Builder()
                        .setId(resultSet.getInt("id"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get accounts from DB", e.getCause());
        }
        return list;
    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        String sql = "SELECT LAST_INSERT_ID() AS lastId FROM accounts";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            logger.error("Can't get last added user account from DB", e.getCause());
        }
        return id;
    }
}

