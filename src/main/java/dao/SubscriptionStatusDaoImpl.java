package dao;

import beans.SubscriptionStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionStatusDaoImpl implements SubscriptionStatusDao {

    @Override
    public void create(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subscription_status (status_name) VALUE ?");
        preparedStatement.setString(1, subscriptionStatus.getStatusName());
        preparedStatement.execute();
    }

    @Override
    public SubscriptionStatus read(int id, Connection connection) throws SQLException {
        SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT status_name FROM subscription_status WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        subscriptionStatus.setId(id);
        subscriptionStatus.setStatusName(resultSet.getString("status_name"));

        return subscriptionStatus;
    }

    @Override
    public void update(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE subscription_status SET status_name=? WHERE id=?");
        preparedStatement.setString(1, subscriptionStatus.getStatusName());
        preparedStatement.setInt(2, subscriptionStatus.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM subscription_status WHERE id=?");
        preparedStatement.setInt(1, subscriptionStatus.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<SubscriptionStatus> getAll(Connection connection) throws SQLException {
        List<SubscriptionStatus> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM subscription_status ORDER BY id");

        while (resultSet.next()) {
            list.add(new SubscriptionStatus.Builder()
                    .setId(resultSet.getInt("id"))
                    .setStatusName(resultSet.getString("status_name"))
                    .build());
        }
        return list;
    }
}
