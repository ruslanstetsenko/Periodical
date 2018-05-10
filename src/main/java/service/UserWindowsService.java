package service;

import connection.ConnectionPool;
import dao.*;
import beens.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserWindowsService {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();

    private List<Publication> publicationList;
    private List<PublicationPeriodicityCost> publicationPeriodicityCostList;

    private int currentPubStatusId = 1;
    private int currentSubscStatusId = 1;
    private int currentPubTypeId = 1;
    private int currentPubThemeId = 1;
    private int currentBillStatus = 1;

    private int publicationsAmount;
    private int subscriptionBillAmount;
    private int subscriptionAmount;

    public Object[] loadUserWindow(int userId) {
        Connection connection = ConnectionPool.getConnection();
//        List<Subscription> subscriptionList = new ArrayList<>();
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
//        List<Publication> publicationList = new ArrayList<>();
        Map<String, Subscription> map = new HashMap<>();

        try {
//            subscriptionList = subscriptionDao.getAll(connection)
//                    .stream()
//                    .filter(subscription -> subscription.getUsersId() == user.getId())
//                    .filter(subscription -> subscription.getSubscriptionStatusId() == 1)//check id
//                    .collect(Collectors.toList());
            subscriptionBillList = subscriptionBillDao.getAll(connection)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == 2)//check id
                    .filter(subscriptionBill -> subscriptionBill.getUserId() == userId)
                    .collect(Collectors.toList());
            map = subscriptionDao.getSubscriprionAndPubName(connection, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Object[]{map, subscriptionBillList};
    }

    public List<Subscription> selectSubscByStatus (SubscriptionStatus status, User user) {
        Connection connection = ConnectionPool.getConnection();
        List<Subscription> subscriptionList = null;
        try {
            subscriptionList = subscriptionDao.getAll(connection)
                    .stream()
                    .filter(subscription -> subscription.getUsersId() == user.getId())
                    .filter(subscription -> subscription.getSubscriptionStatusId() == status.getId())
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionList;
    }

    public void showAboutSubscription(Subscription subscription) {
        Connection connection = ConnectionPool.getConnection();
        try {
            Publication publication = publicationDao.read(subscription.getPublicationId(), connection);
            PublicationStatus publicationStatus = publicationStatusDao.read(publication.getPublicationStatusId(), connection);
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
//subscribe window
    public void selectActivePublications() {
        Connection connection = ConnectionPool.getConnection();
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationStatusId() == 1)
                    .collect(Collectors.toList());
            publicationsAmount = publicationList.size();
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

    public void selectPublByTypeByTheme (Publication publication) {
        Connection connection = ConnectionPool.getConnection();
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication1 -> publication1.getPublicationStatusId() == 1)
                    .filter(publication1 -> publication1.getPublicationTypeId() == publication.getPublicationTypeId())
                    .filter(publication1 -> publication1.getPublicationThemeId() == publication.getPublicationThemeId())
                    .collect(Collectors.toList());
            publicationsAmount = publicationList.size();
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
}
