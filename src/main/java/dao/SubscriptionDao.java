package dao;

import beans.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SubscriptionDao {

    public void create(Subscription subscription, Connection connection) throws SQLException;
    public Subscription read(int id, Connection connection) throws SQLException;
    public Subscription readByBill(int subscBillId, Connection connection) throws SQLException;
    public void update(Subscription subscription, Connection connection) throws SQLException;
    public void delete(Subscription subscription, Connection connection) throws SQLException;
    public List<Subscription> getAll(Connection connection) throws SQLException;
    public Map<String, Subscription> getSubscByBillByUser(Connection connection, int userId, int billId) throws SQLException;
    public Map<String, Subscription> getSubscByUser(Connection connection, int userId) throws SQLException;
    public Map<String, Subscription> getSubscByStatusByUser(Connection connection, int userId, int subsStatus) throws SQLException;
    public Map<String, Subscription> getSubscByBill(Connection connection, int billId) throws SQLException;


}
