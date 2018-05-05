package service;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.SubscriptionBillDao;
import entity.Subscription;
import entity.SubscriptionBill;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionBillService {

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

    //create biil and subscription
    public void createBill(User user, List<Subscription> subscriptionList) {
        Connection connection = ConnectionPool.getConnection();
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        subscriptionBill.setUserId(user.getId());
        subscriptionBill.setTotalCost(subscriptionList.stream().mapToDouble(Subscription::getSubscriptionCost).sum());
        subscriptionBill.setBillNumber(subscriptionBill.getBillSetDay().toString() + user.getId());
        try {
            subscriptionBillDao.create(subscriptionBill, connection);
            int subscriptionBillId = subscriptionBillDao.readLastId(connection);
            for (Subscription subscription : subscriptionList) {
                new SubscriptionService().createSubscription(connection, subscription, subscriptionBillId);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
