package service;

import beans.*;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.SubscriptionDao;
import dao.interfaces.SubscriptionStatusDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

public class SubscriptionService {
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionService.class);

    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private SubscriptionStatusDao subscriptionStatusDao = DaoFactory.getSubscriptionStatusDao();

    public void createSubscription(int userId, List<PublicationPeriodicyCost> publicationPeriodicyCostList, List<Publication> publicationList, List<Integer> periodicyCostId) {
        Connection connection = ConnectionPool.getConnection(false);
        Subscription subscription;
        PublicationPeriodicyCost costBean;
        PublicationPeriodicityCostService costService = new PublicationPeriodicityCostService();
        PublicationService publicationService = new PublicationService();

        try {
            for (Integer costId : periodicyCostId) {
                costBean = costService.getPubPeriodicyCost(costId);
                publicationPeriodicyCostList.add(costBean);
                publicationList.add(publicationService.getPublication(costBean.getPublicationId()));
            }

            int billId = new SubscriptionBillService().createBill(connection, userId, publicationPeriodicyCostList);

            for (int i = 0; i < publicationList.size(); i++) {
                subscription = new Subscription.Builder()
                        .setSubscriptionDate(new Date(new java.util.Date().getTime()))
                        .setSubscriptionCost(publicationPeriodicyCostList.get(i).getCost())
                        .setPublicationId(publicationList.get(i).getId())
                        .setSubscriptionStatusId(3)
                        .setSubscriptionBillsId(billId)
                        .setUsersId(userId)
                        .build();
                subscriptionDao.create(subscription, connection);
            }
            ConnectionPool.commitTransaction(connection);
        } catch (DataBaseWorkException e) {
            ConnectionPool.transactionRollback(connection);
            LOGGER.error("Can't create subscription", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public Subscription getSubscription(int subsId) {
        Connection connection = ConnectionPool.getConnection(true);
        Subscription subscription = null;
        try {
            subscription = subscriptionDao.read(subsId, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get subscriptions", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return subscription;
    }

    public Map<String, Subscription> getSubscByBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap;
        try {
            subscriptionMap = subscriptionDao.getSubscByBill(connection, billId);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get subscriptions", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return subscriptionMap;
    }

    public List<SubscriptionStatus> getSubsStatusList() {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionStatus> subscriptionStatusList;
        try {
            subscriptionStatusList = subscriptionStatusDao.getAll(connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get subscriptions", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return subscriptionStatusList;
    }
}
