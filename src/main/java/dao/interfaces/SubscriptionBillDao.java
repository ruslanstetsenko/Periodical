package dao.interfaces;

import beans.SubscriptionBill;
//import connection.ConnectionPool;
//import javax.persistence.SqlResultSetMapping;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionBillDao {

    void create(SubscriptionBill subscriptionBill, Connection connection);
    SubscriptionBill read(int id, Connection connection);
    void update(SubscriptionBill subscriptionBill, Connection connection);
    void delete(int subscriptionBillId, Connection connection);
    List<SubscriptionBill> getAll(Connection connection);
    int readLastId(Connection connection);
    List<SubscriptionBill> getByStatus(Connection connection, int statusId);
    List<SubscriptionBill> getByUser(Connection connection, int subscriptionId);

}
