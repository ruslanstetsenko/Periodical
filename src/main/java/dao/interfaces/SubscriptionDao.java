package dao.interfaces;

import beans.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SubscriptionDao {

    void create(Subscription subscription, Connection connection);
    Subscription read(int id, Connection connection);
    Subscription readByBill(int subscBillId, Connection connection);
    void update(Subscription subscription, Connection connection);
    void delete(Subscription subscription, Connection connection);
    List<Subscription> getAll(Connection connection);
    Map<String, Subscription> getSubscByBillByUser(Connection connection, int userId, int billId);
    Map<String, Subscription> getSubscByUser(Connection connection, int userId);
    Map<String, Subscription> getSubscByStatusByUser(Connection connection, int userId, int subsStatus);
    Map<String, Subscription> getSubscByBill(Connection connection, int billId);


}
