package dao.implementations;

import beans.SubscriptionBill;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.SubscriptionBillDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionBillDaoImpl implements SubscriptionBillDao {
    private static final Logger logger = LogManager.getLogger(SubscriptionBillDaoImpl.class);

    @Override
    public void create(SubscriptionBill subscriptionBill, Connection connection) {
        String sql = "INSERT INTO subscription_bills (total_cost, validity_period, paid, bill_nimber, bill_set_day, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, subscriptionBill.getTotalCost());
            preparedStatement.setInt(2, subscriptionBill.getValidityPeriod());
            preparedStatement.setInt(3, subscriptionBill.getPaid());
            preparedStatement.setString(4, subscriptionBill.getBillNumber());
            preparedStatement.setDate(5, subscriptionBill.getBillSetDay());
            preparedStatement.setInt(6, subscriptionBill.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create bill in DB", e.getCause());
        }
    }

    @Override
    public SubscriptionBill read(int id, Connection connection) {
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        String sql = "SELECT total_cost, validity_period, paid, bill_nimber, bill_set_day,user_id FROM subscription_bills WHERE id=? ORDER BY paid ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subscriptionBill.setId(id);
                subscriptionBill.setTotalCost(resultSet.getDouble("total_cost"));
                subscriptionBill.setValidityPeriod(resultSet.getInt("validity_period"));
                subscriptionBill.setPaid(resultSet.getInt("paid"));
                subscriptionBill.setBillNumber(resultSet.getString("bill_nimber"));
                subscriptionBill.setBillSetDay(resultSet.getDate("bill_set_day"));
                subscriptionBill.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't read bill from DB", e.getCause());
        }
        return subscriptionBill;
    }

    @Override
    public void update(SubscriptionBill subscriptionBill, Connection connection) {
        String sql = "UPDATE subscription_bills SET total_cost=?, validity_period=?, paid=?, bill_nimber=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, subscriptionBill.getTotalCost());
            preparedStatement.setInt(2, subscriptionBill.getValidityPeriod());
            preparedStatement.setInt(3, subscriptionBill.getPaid());
            preparedStatement.setString(4, subscriptionBill.getBillNumber());
            preparedStatement.setInt(5, subscriptionBill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update bill in DB", e.getCause());
        }
    }

    @Override
    public void delete(int subscriptionBillId, Connection connection) {
        String sql = "DELETE FROM subscription_bills WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, subscriptionBillId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bill in DB", e.getCause());
        }
    }

    @Override
    public List<SubscriptionBill> getAll(Connection connection) {
        List<SubscriptionBill> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription_bills ORDER BY paid ASC";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new SubscriptionBill.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTotalCost(resultSet.getDouble("total_cost"))
                        .setValidityPeriod(resultSet.getInt("validity_period"))
                        .setPaid(resultSet.getByte("paid"))
                        .setBillNumber(resultSet.getString("bill_nimber"))
                        .setBillSetDay(resultSet.getDate("bill_set_day"))
                        .setUserId(resultSet.getInt("user_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get bills from DB", e.getCause());
        }
        return list;
    }

    @Override
    public int readLastId(Connection connection) {
        int id = 0;
        String sql = "SELECT MAX(id) FROM subscription_bills";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't read last added bill in DB", e.getCause());
        }
        return id;
    }

    @Override
    public List<SubscriptionBill> getByStatus(Connection connection, int paid) {
        List<SubscriptionBill> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription_bills WHERE paid=? ORDER BY paid ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, paid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new SubscriptionBill.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTotalCost(resultSet.getDouble("total_cost"))
                        .setValidityPeriod(resultSet.getInt("validity_period"))
                        .setPaid(paid)
                        .setBillNumber(resultSet.getString("bill_nimber"))
                        .setBillSetDay(resultSet.getDate("bill_set_day"))
                        .setUserId(resultSet.getInt("user_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get bill by status from DB", e.getCause());
        }
        return list;
    }

    @Override
    public List<SubscriptionBill> getByUser(Connection connection, int userId) {
        List<SubscriptionBill> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription_bills WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new SubscriptionBill.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTotalCost(resultSet.getDouble("total_cost"))
                        .setValidityPeriod(resultSet.getInt("validity_period"))
                        .setPaid(resultSet.getInt("paid"))
                        .setBillNumber(resultSet.getString("bill_nimber"))
                        .setBillSetDay(resultSet.getDate("bill_set_day"))
                        .setUserId(userId)
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get bill by user from DB", e.getCause());
        }
        return list;
    }
}
