package service;

import beens.SubscriptionStatus;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.PublicationDao;
import dao.SubscriptionDao;
import beens.Publication;
import beens.Subscription;
import beens.User;
import dao.SubscriptionStatusDao;

import javax.management.DescriptorAccess;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubscriptionService {

    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private SubscriptionStatusDao subscriptionStatusDao = DaoFactory.getSubscriptionStatusDao();
    private PublicationDao publicationDao = DaoFactory.getPublicationDao();

    public void createSubscription(Connection connection, Subscription subscription, int subscriptionBillId) {
        subscription.setSubscriptionBillsId(subscriptionBillId);
        try {
            subscriptionDao.create(subscription, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Subscription getSubscription(int subsId) {
        Connection connection = ConnectionPool.getConnection(true);
        Subscription subscription = new Subscription();
        try {
            subscription = subscriptionDao.read(subsId, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscription;
    }

    public void addSubscriptionToList(User user, Publication publication, String subsType, Double cost) {
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(new Subscription.Builder().setSubscriptionDate((Date) Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())).setSubscriptionType(subsType).setSubscriptionCost(cost).setPublicationId(publication.getId()).setSubscriptionStatusId(1).build());
    }

    public List<Subscription> getSubscriptionsByBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Subscription> subscriptionList = new ArrayList<>();
        try {
            subscriptionDao.getAll(connection)
                    .stream()
                    .filter(subscription -> subscription.getSubscriptionBillsId() == billId)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptionList;
    }

    public Map<String, Subscription> getSubscByBillByUser(int userId, int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = new LinkedHashMap<>();
        try {
            subscriptionMap = subscriptionDao.getSubscByBillByUser(connection, userId, billId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionMap;
    }

    public Map<String, Subscription> getSubscByBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = new LinkedHashMap<>();
        try {
            subscriptionMap = subscriptionDao.getSubscByBill(connection, billId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionMap;
    }

    public Map<String, Subscription> getSubscById(int subsId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> subscriptionMap = new LinkedHashMap<>();
        try {
            Subscription subscription = subscriptionDao.read(subsId, connection);
            Publication publication = publicationDao.read(subscription.getPublicationId(), connection);
            subscriptionMap.put(publication.getName(), subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionMap;
    }

    public List<SubscriptionStatus> getSubsStatusList() {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionStatus> subscriptionStatusList = new ArrayList<>();
        try {
            subscriptionStatusList = subscriptionStatusDao.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionStatusList;
    }
}
