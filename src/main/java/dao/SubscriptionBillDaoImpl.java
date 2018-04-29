package dao;

import entity.SubscriptionBill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionBillDaoImpl implements SubscriptionBillDao {

    @Override
    public void create(SubscriptionBill subscriptionBill, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subscription_bills (total_cost, validity_period, paid, bill_nimber) VALUES (?, ?, ?, ?)");
        preparedStatement.setDouble(1, subscriptionBill.getTotalCost());
        preparedStatement.setInt(2, subscriptionBill.getValidityPeriod());
        preparedStatement.setByte(3, subscriptionBill.getPaid());
        preparedStatement.setString(4, subscriptionBill.getBillNumber());

        preparedStatement.execute();
    }

    @Override
    public SubscriptionBill read(int id, Connection connection) throws SQLException {
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_cost, validity_period, paid, bill_nimber FROM subscription_bills WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        subscriptionBill.setId(id);
        subscriptionBill.setTotalCost(resultSet.getDouble("total_cost"));
        subscriptionBill.setValidityPeriod(resultSet.getInt("validity_period"));
        subscriptionBill.setPaid(resultSet.getByte("paid"));
        subscriptionBill.setBillNumber(resultSet.getString("bill_nimber"));

        return subscriptionBill;
    }

    @Override
    public void update(SubscriptionBill subscriptionBill, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE subscription_bills SET total_cost=?, validity_period=?, paid=?, bill_nimber=? WHERE id=?");
        preparedStatement.setDouble(1, subscriptionBill.getTotalCost());
        preparedStatement.setInt(2, subscriptionBill.getValidityPeriod());
        preparedStatement.setByte(3, subscriptionBill.getPaid());
        preparedStatement.setString(4, subscriptionBill.getBillNumber());
        preparedStatement.setInt(5, subscriptionBill.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(SubscriptionBill subscriptionBill, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM subscription_bills WHERE id=?");
        preparedStatement.setInt(1, subscriptionBill.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<SubscriptionBill> getAll(Connection connection) throws SQLException {
        List<SubscriptionBill> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM subscription_bills ORDER BY id");

        while (resultSet.next()) {
            list.add(new SubscriptionBill.Builder()
                    .setId(resultSet.getInt("id"))
                    .setTotalCost(resultSet.getDouble("total_cost"))
                    .setValidityPeriod(resultSet.getInt("validity_period"))
                    .setPaid(resultSet.getByte("paid"))
                    .setBillNumber(resultSet.getString("bill_number"))
                    .build());
        }
        return list;
    }
}
