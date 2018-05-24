package dao.implementations;

import beans.SubscriptionStatus;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.SubscriptionStatusDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionStatusDaoImpl implements SubscriptionStatusDao {
    private static final Logger logger = LogManager.getLogger(SubscriptionStatusDaoImpl.class);

    @Override
    public void create(SubscriptionStatus subscriptionStatus, Connection connection) {
        String sql = "INSERT INTO subscription_status (status_name) VALUE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, subscriptionStatus.getStatusName());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create subscription status in DB", e.getCause());
        }
    }

    @Override
    public SubscriptionStatus read(int id, Connection connection) {
        SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
        String sql = "SELECT status_name FROM subscription_status WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subscriptionStatus.setId(id);
                subscriptionStatus.setStatusName(resultSet.getString("status_name"));
            }
        } catch (SQLException e) {
            logger.error("Can't read subscription status from DB", e.getCause());
        }
        return subscriptionStatus;
    }

    @Override
    public void update(SubscriptionStatus subscriptionStatus, Connection connection) {
        String sql = "UPDATE subscription_status SET status_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, subscriptionStatus.getStatusName());
            preparedStatement.setInt(2, subscriptionStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update subscription status in DB", e.getCause());
        }
    }

    @Override
    public void delete(SubscriptionStatus subscriptionStatus, Connection connection) {
        String sql = "DELETE FROM subscription_status WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, subscriptionStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete subscription status in DB", e.getCause());
        }
    }

    @Override
    public List<SubscriptionStatus> getAll(Connection connection) {
        List<SubscriptionStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription_status ORDER BY id";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new SubscriptionStatus.Builder()
                        .setId(resultSet.getInt("id"))
                        .setStatusName(resultSet.getString("status_name"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription statuses from DB", e.getCause());
        }
        return list;
    }
}
