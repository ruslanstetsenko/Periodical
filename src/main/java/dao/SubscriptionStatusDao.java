package dao;

import beens.SubscriptionStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionStatusDao {

    public void create(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException;
    public SubscriptionStatus read(int id, Connection connection) throws SQLException;
    public void update(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException;
    public void delete(SubscriptionStatus subscriptionStatus, Connection connection) throws SQLException;
    public List<SubscriptionStatus> getAll(Connection connection) throws SQLException;

}
