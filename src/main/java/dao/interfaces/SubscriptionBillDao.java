package dao.interfaces;

import beans.SubscriptionBill;
import exceptions.DataBaseWorkException;
//import connection.ConnectionPool;
//import javax.persistence.SqlResultSetMapping;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionBillDao {

    int create(SubscriptionBill subscriptionBill, Connection connection) throws DataBaseWorkException;
    SubscriptionBill read(int id, Connection connection) throws DataBaseWorkException;
    void update(SubscriptionBill subscriptionBill, Connection connection) throws DataBaseWorkException;
    void delete(int subscriptionBillId, Connection connection) throws DataBaseWorkException;
    List<SubscriptionBill> getAll(Connection connection) throws DataBaseWorkException;
    int readLastId(Connection connection) throws DataBaseWorkException;
    List<SubscriptionBill> getByStatus(Connection connection, int statusId) throws DataBaseWorkException;
    List<SubscriptionBill> getByUser(Connection connection, int subscriptionId) throws DataBaseWorkException;

}
