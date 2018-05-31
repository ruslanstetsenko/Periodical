package dao.implementations;

import beans.SubscriptionBill;
import beans.User;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.SubscriptionBillDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionBillDaoImpl implements SubscriptionBillDao {
    private static final Logger logger = LogManager.getLogger(SubscriptionBillDaoImpl.class);

    @Override
    public int create(SubscriptionBill subscriptionBill, Connection connection) {
        String sql = "INSERT INTO subscription_bills (total_cost, validity_period, paid, bill_nimber, bill_set_day, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, subscriptionBill.getTotalCost());
            preparedStatement.setInt(2, subscriptionBill.getValidityPeriod());
            preparedStatement.setInt(3, subscriptionBill.getPaid());
            preparedStatement.setString(4, subscriptionBill.getBillNumber());
            preparedStatement.setDate(5, subscriptionBill.getBillSetDay());
            preparedStatement.setInt(6, subscriptionBill.getUserId());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create bill in DB", e.getCause());
            throw new DataBaseWorkException("message.error.bill");
        }
        return id;
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
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
            throw new DataBaseWorkException("message.error.bill");
        }
        return list;
    }

    @Override
    public Map<SubscriptionBill, User> getBillWithUsersByStatus(Connection connection, int paid) {
        Map<SubscriptionBill, User> map = new LinkedHashMap<>();

        String sql = "SELECT * FROM subscription_bills INNER JOIN users ON subscription_bills.user_id = users.id WHERE paid =? ORDER BY users.surname ASC";
        if (paid == 0) {
            sql = "SELECT * FROM subscription_bills INNER JOIN users ON subscription_bills.user_id = users.id ORDER BY users.surname ASC";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (paid != 0) {
                preparedStatement.setInt(1, paid);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubscriptionBill bill = new SubscriptionBill.Builder()
                        .setId(resultSet.getInt(1))
                        .setTotalCost(resultSet.getDouble("total_cost"))
                        .setValidityPeriod(resultSet.getInt("validity_period"))
                        .setPaid(resultSet.getInt("paid"))
                        .setBillNumber(resultSet.getString("bill_nimber"))
                        .setBillSetDay(resultSet.getDate("bill_set_day"))
                        .setUserId(resultSet.getInt("user_id"))
                        .build();
                User user = new User.Builder()
                        .setId(resultSet.getInt(8))
                        .setSurname(resultSet.getString("surname"))
                        .setName(resultSet.getString("name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setBirthday(resultSet.getDate("birthday"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setPassportIdentNumberId(resultSet.getInt("passport_ident_number_id"))
                        .setAccountsId(resultSet.getInt("accounts_id"))
                        .setLivingAddressId(resultSet.getInt("living_address_id"))
                        .setContactInfoId(resultSet.getInt("contact_info_id"))
                        .setUserRoleId(resultSet.getInt("user_role_id"))
                        .build();

                map.put(bill, user);
            }
        } catch (SQLException e) {
            logger.error("Can't get subscription by status, user from DB", e.getCause());
            throw new DataBaseWorkException("message.error.subscription");
        }
        return map;
    }
}
