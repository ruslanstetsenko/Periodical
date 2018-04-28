package dao;

import entity.SubscriptionBill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionBillDao {

    public void create(SubscriptionBill subscriptionBill, Connection connection) throws SQLException;
    public SubscriptionBill read(int id, Connection connection) throws SQLException;
    public void update(SubscriptionBill subscriptionBill, Connection connection) throws SQLException;
    public void delete(SubscriptionBill subscriptionBill, Connection connection) throws SQLException;
    public List<SubscriptionBill> getAll(Connection connection) throws SQLException;

}
