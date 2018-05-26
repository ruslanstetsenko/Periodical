package dao.implementations;

import beans.Subscription;
import dao.interfaces.SubscriptionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class SubscriptionDaoImpl implements SubscriptionDao {
    private static final Logger logger = LogManager.getLogger(SubscriptionDaoImpl.class);

    @Override
    public void create(Subscription subscription, Connection connection) {
        String sql = "INSERT INTO subscription (subscription_date, subscription_cost, publication_id, subscription_status_id, users_id, subscription_bills_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, subscription.getSubscriptionDate());
            preparedStatement.setDouble(2, subscription.getSubscriptionCost());
            preparedStatement.setInt(3, subscription.getPublicationId());
            preparedStatement.setInt(4, subscription.getSubscriptionStatusId());
            preparedStatement.setInt(5, subscription.getUsersId());
            preparedStatement.setInt(6, subscription.getSubscriptionBillsId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create subscription in DB", e.getCause());
        }

    }

    @Override
    public Subscription read(int id, Connection connection) {
        Subscription subscription = new Subscription();
        String sql = "SELECT subscription_date, subscription_cost, publication_id,subscription_status_id,users_id, subscription_bills_id FROM subscription WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subscription.setId(id);
                subscription.setSubscriptionDate(resultSet.getDate("subscription_date"));
                subscription.setSubscriptionCost(resultSet.getDouble("subscription_cost"));
                subscription.setPublicationId(resultSet.getInt("publication_id"));
                subscription.setSubscriptionStatusId(resultSet.getInt("subscription_status_id"));
                subscription.setUsersId(resultSet.getInt("users_id"));
                subscription.setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't read subscription from DB", e.getCause());
        }
        return subscription;
    }

    @Override
    public Subscription readByBill(int subscBillId, Connection connection) {
        Subscription subscription = new Subscription();
        String sql = "SELECT id, subscription_date, subscription_cost, publication_id,subscription_status_id,users_id, subscription_bills_id FROM subscription WHERE subscription_bills_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, subscBillId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subscription.setId(resultSet.getInt("id"));
                subscription.setSubscriptionDate(resultSet.getDate("subscription_date"));
                subscription.setSubscriptionCost(resultSet.getDouble("subscription_cost"));
                subscription.setPublicationId(resultSet.getInt("publication_id"));
                subscription.setSubscriptionStatusId(resultSet.getInt("subscription_status_id"));
                subscription.setUsersId(resultSet.getInt("user_id"));
                subscription.setSubscriptionBillsId(subscBillId);
            }
        } catch (SQLException e) {
            logger.error("Can't read subscription by bill from DB", e.getCause());
        }
        return subscription;
    }

    @Override
    public void update(Subscription subscription, Connection connection) {
        String sql = "UPDATE subscription SET subscription_date=?, subscription_cost=?, publication_id=?, subscription_status_id=?, users_id=?, subscription_bills_id=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, subscription.getSubscriptionDate());
            preparedStatement.setDouble(2, subscription.getSubscriptionCost());
            preparedStatement.setInt(3, subscription.getPublicationId());
            preparedStatement.setInt(4, subscription.getSubscriptionStatusId());
            preparedStatement.setInt(5, subscription.getUsersId());
            preparedStatement.setInt(6, subscription.getSubscriptionBillsId());
            preparedStatement.setInt(7, subscription.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update subscription in DB", e.getCause());
        }
    }

    @Override
    public void delete(Subscription subscription, Connection connection) {
        String sql = "DELETE FROM subscription WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, subscription.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete subscription in DB", e.getCause());
        }
    }

    @Override
    public List<Subscription> getAll(Connection connection) {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Subscription.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSubscriptionDate(resultSet.getDate("subscription_date"))
                        .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .setSubscriptionStatusId(resultSet.getInt("subscription_status_id"))
                        .setUsersId(resultSet.getInt("users_id"))
                        .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscriptions from DB", e.getCause());
        }
        return list;
    }

    @Override
    public Map<String, Subscription> getSubscByBillByUser(Connection connection, int userId, int billId) {
        Map<String, Subscription> map = new LinkedHashMap<>();
        String sql = "SELECT * FROM subscription INNER JOIN publication ON subscription.publication_id = publication.id WHERE subscription.users_id =? AND subscription_bills_id =? ORDER BY subscription_status_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, billId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("name"), new Subscription.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSubscriptionDate(resultSet.getDate("subscription_date"))
                        .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .setSubscriptionStatusId(resultSet.getInt("subscription_status_id"))
                        .setUsersId(resultSet.getInt("users_id"))
                        .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription by bill, user in DB", e.getCause());
        }
        return map;
    }

    @Override
    public Map<String, Subscription> getSubscByUser(Connection connection, int userId) {
        Map<String, Subscription> map = new LinkedHashMap<>();
        String sql = "SELECT * FROM subscription INNER JOIN publication ON subscription.publication_id = publication.id WHERE users_id =? ORDER BY subscription_status_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("name"), new Subscription.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSubscriptionDate(resultSet.getDate("subscription_date"))
                        .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .setSubscriptionStatusId(resultSet.getInt("subscription_status_id"))
                        .setUsersId(resultSet.getInt("users_id"))
                        .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription by user from DB", e.getCause());
        }
        return map;
    }

    @Override
    public Map<String, Subscription> getSubscByStatusByUser(Connection connection, int userId, int subsStatus) {
        Map<String, Subscription> map = new LinkedHashMap<>();
        String sql = "SELECT * FROM subscription INNER JOIN publication ON subscription.publication_id = publication.id WHERE users_id =? AND subscription_status_id=? ORDER BY subscription_status_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subsStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("name"), new Subscription.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSubscriptionDate(resultSet.getDate("subscription_date"))
                        .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .setSubscriptionStatusId(subsStatus)
                        .setUsersId(userId)
                        .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription by status, user from DB", e.getCause());
        }
        return map;
    }

    @Override
    public Map<String, Subscription> getSubscByBill(Connection connection, int billId) {
        Map<String, Subscription> map = new LinkedHashMap<>();
        String sql = "SELECT * FROM subscription INNER JOIN publication ON subscription.publication_id = publication.id WHERE subscription_bills_id =? ORDER BY subscription_status_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("name"), new Subscription.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSubscriptionDate(resultSet.getDate("subscription_date"))
                        .setSubscriptionCost(resultSet.getDouble("subscription_cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .setSubscriptionStatusId(resultSet.getInt("subscription_status_id"))
                        .setUsersId(resultSet.getInt("users_id"))
                        .setSubscriptionBillsId(resultSet.getInt("subscription_bills_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription by bill from DB", e.getCause());
        }
        return map;
    }
}
