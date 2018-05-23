package dao.interfaces;

import beans.SubscriptionStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionStatusDao {

    void create(SubscriptionStatus subscriptionStatus, Connection connection);
    SubscriptionStatus read(int id, Connection connection);
    void update(SubscriptionStatus subscriptionStatus, Connection connection);
    void delete(SubscriptionStatus subscriptionStatus, Connection connection);
    List<SubscriptionStatus> getAll(Connection connection);

}
