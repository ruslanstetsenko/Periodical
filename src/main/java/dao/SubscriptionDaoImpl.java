package dao;

import entity.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public void create(Subscription subscription, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subscription (subscription_date, subscription_type, subscription_cost, publication_id, subscription_status_id, users_id, subscription_bills_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setDate(1, subscription.getSubscriptionDate());
        preparedStatement.setString(2, subscription.getSubscriptionType());
        preparedStatement.setDouble(3, subscription.getSubscriptionCost());
        preparedStatement.setInt(4, subscription.getPublicationId());
        preparedStatement.setInt(1, subscription.getSubscriptionStatusId());
        preparedStatement.setInt(1, subscription.getUsersId());
        preparedStatement.setInt(1, subscription.getSubscriptionBillsId());
        preparedStatement.execute();
    }

    @Override
    public Subscription read(int id, Connection connection) throws SQLException {
        Subscription subscription = new Subscription();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT subscription_date, subscription_type, subscription_cost, publication_id,subscription_status_id,users_id, subscription_bills_id FROM subscription WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        subscription.setId(id);
        subscription.setSubscriptionDate(resultSet.getDate("subscription_date"));
        subscription.setSubscriptionType(resultSet.getString("subscription_type"));
        subscription.setSubscriptionCost(resultSet.getDouble("subscription_cost"));
        subscription.setPublicationId(resultSet.getInt("publication_id"));
        subscription.setSubscriptionStatusId(resultSet.getInt("subscription_status_id"));
        subscription.setUsersId(resultSet.getInt("user_id"));
        subscription.setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"));

        return subscription;
    }

    @Override
    public void update(Subscription subscription, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE subscription SET subscription_date=?, subscription_type=?, subscription_cost=?, publication_id=?, subscription_status_id=?, users_id=?, subscription_bills_id=? WHERE id=?");
        preparedStatement.setDate(1, subscription.getSubscriptionDate());
        preparedStatement.setString(2, subscription.getSubscriptionType());
        preparedStatement.setDouble(3, subscription.getSubscriptionCost());
        preparedStatement.setInt(4, subscription.getPublicationId());
        preparedStatement.setInt(5, subscription.getSubscriptionStatusId());
        preparedStatement.setInt(6, subscription.getUsersId());
        preparedStatement.setInt(7, subscription.getSubscriptionBillsId());
        preparedStatement.setInt(8, subscription.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Subscription subscription, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE id=?");
        preparedStatement.setInt(1, subscription.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Subscription> getAll(Connection connection) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM subscription ORDER BY id");

        while (resultSet.next()) {
            list.add(new Subscription.Builder()
                    .setId(resultSet.getInt("id"))
                    .setSubscriptionDate(resultSet.getDate("subscription_date"))
                    .setSubscriptionType(resultSet.getString("subscription_type"))
                    .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                    .setPublicationId(resultSet.getInt("publication_id"))
                    .setSubscriptionStatusId(resultSet.getInt("subscription_status_id"))
                    .setUsersId(resultSet.getInt("users_id"))
                    .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                    .build());
        }
        return list;
    }
}
