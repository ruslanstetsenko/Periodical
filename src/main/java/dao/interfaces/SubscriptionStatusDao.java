package dao.interfaces;

import beans.SubscriptionStatus;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionStatusDao {

    int create(SubscriptionStatus subscriptionStatus, Connection connection) throws DataBaseWorkException;
    SubscriptionStatus read(int id, Connection connection) throws DataBaseWorkException;
    void update(SubscriptionStatus subscriptionStatus, Connection connection) throws DataBaseWorkException;
    void delete(SubscriptionStatus subscriptionStatus, Connection connection) throws DataBaseWorkException;
    List<SubscriptionStatus> getAll(Connection connection) throws DataBaseWorkException;

}
