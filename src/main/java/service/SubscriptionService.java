package service;

import beans.*;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.PublicationDao;
import dao.interfaces.SubscriptionDao;
import dao.interfaces.SubscriptionStatusDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class SubscriptionService {

    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private SubscriptionStatusDao subscriptionStatusDao = DaoFactory.getSubscriptionStatusDao();
    private PublicationDao publicationDao = DaoFactory.getPublicationDao();

    public void createSubscription(int userId, List<PublicationPeriodicyCost> publicationPeriodicyCostList, List<Publication> publicationList, List<Integer> periodicyCostId) {
        Connection connection = ConnectionPool.getConnection(false);
        Subscription subscription;
        PublicationPeriodicyCost costBean;
        PublicationPeriodicityCostService costService = new PublicationPeriodicityCostService();
        PublicationService publicationService = new PublicationService();

        for (Integer costId : periodicyCostId) {
            costBean = costService.getPubPeriodicyCost(costId);
            publicationPeriodicyCostList.add(costBean);
            publicationList.add(publicationService.getPublication(costBean.getPublicationId()));
        }

        try {

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
            connection.commit();
        } catch (SQLException e) {
            ConnectionPool.transactionRollback(connection);
        } finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public Subscription getSubscription(int subsId) {
        Connection connection = ConnectionPool.getConnection(true);
        Subscription subscription = subscriptionDao.read(subsId, connection);

        ConnectionPool.closeConnection(connection);
        return subscription;
    }

    public void addSubscriptionToList(User user, Publication publication, Double cost) {
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(new Subscription.Builder().setSubscriptionDate((Date) Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())).setSubscriptionCost(cost).setPublicationId(publication.getId()).setSubscriptionStatusId(1).build());
    }

//    public List<Subscription> getSubscriptionsByBill(int billId) {
//        Connection connection = ConnectionPool.getConnection(true);
//        List<Subscription> subscriptionList = new ArrayList<>();
//
//        subscriptionDao.getAll(connection)
//                .stream()
//                .filter(subscription -> subscription.getSubscriptionBillsId() == billId)
//                .collect(Collectors.toList());
//
//        ConnectionPool.closeConnection(connection);
//        return subscriptionList;
//    }

    public Map<String, Subscription> getSubscByBillByUser(int userId, int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = new LinkedHashMap<>();
        subscriptionMap = subscriptionDao.getSubscByBillByUser(connection, userId, billId);

        ConnectionPool.closeConnection(connection);
        return subscriptionMap;
    }

    public Map<String, Subscription> getSubscByBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = subscriptionDao.getSubscByBill(connection, billId);

        ConnectionPool.closeConnection(connection);
        return subscriptionMap;
    }

    public Map<String, Subscription> getSubscById(int subsId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = new LinkedHashMap<>();
        Subscription subscription = subscriptionDao.read(subsId, connection);
        Publication publication = publicationDao.read(subscription.getPublicationId(), connection);
        subscriptionMap.put(publication.getName(), subscription);

        ConnectionPool.closeConnection(connection);
        return subscriptionMap;
    }

    public List<SubscriptionStatus> getSubsStatusList() {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionStatus> subscriptionStatusList = new ArrayList<>();
        subscriptionStatusList = subscriptionStatusDao.getAll(connection);

        ConnectionPool.closeConnection(connection);
        return subscriptionStatusList;
    }
}
