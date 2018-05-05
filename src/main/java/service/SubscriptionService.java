package service;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.SubscriptionDao;
import entity.Publication;
import entity.Subscription;
import entity.SubscriptionBill;
import entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class SubscriptionService {

    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private List<Subscription> subscriptionList;

    public void createSubscription(Connection connection, Subscription subscription, int subscriptionBillId) {
        subscription.setSubscriptionBillsId(subscriptionBillId);
        try {
            subscriptionDao.create(subscription, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSubscriptionToList(User user, Publication publication, String subsType, Double cost) {
        subscriptionList.add(new Subscription.Builder().setSubscriptionDate((Date) Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())).setSubscriptionType(subsType).setSubscriptionCost(cost).setPublicationId(publication.getId()).setSubscriptionStatusId(1).build());
    }

}
