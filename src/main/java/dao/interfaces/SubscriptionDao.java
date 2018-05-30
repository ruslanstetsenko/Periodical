package dao.interfaces;

import beans.Subscription;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SubscriptionDao {

    int create(Subscription subscription, Connection connection) throws DataBaseWorkException;
    Subscription read(int id, Connection connection) throws DataBaseWorkException;
    Subscription readByBill(int subscBillId, Connection connection) throws DataBaseWorkException;
    void update(Subscription subscription, Connection connection) throws DataBaseWorkException;
    void delete(Subscription subscription, Connection connection) throws DataBaseWorkException;
    List<Subscription> getAll(Connection connection) throws DataBaseWorkException;
    Map<String, Subscription> getSubscByBillByUser(Connection connection, int userId, int billId) throws DataBaseWorkException;
    Map<String, Subscription> getSubscByUser(Connection connection, int userId) throws DataBaseWorkException;
    Map<String, Subscription> getSubscByStatusByUser(Connection connection, int userId, int subsStatus) throws DataBaseWorkException;
    Map<String, Subscription> getSubscByBill(Connection connection, int billId) throws DataBaseWorkException;


}
