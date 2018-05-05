package dao;

import entity.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {

    public void create(Subscription subscription, Connection connection) throws SQLException;
    public Subscription read(int id, Connection connection) throws SQLException;
    public Subscription readByBill(int subscBillId, Connection connection) throws SQLException;
    public void update(Subscription subscription, Connection connection) throws SQLException;
    public void delete(Subscription subscription, Connection connection) throws SQLException;
    public List<Subscription> getAll(Connection connection) throws SQLException;

}
