package service;

import connection.ConnectionPool;
import dao.*;
import beens.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserWindows {

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
    private List<SubscriptionBill> subscriptionBillList;
    private List<Subscription> subscriptionList;

    private int currentPubStatusId = 1;
    private int currentSubscStatusId = 1;
    private int currentPubTypeId = 1;
    private int currentPubThemeId = 1;
    private int currentBillStatus = 1;

    private int publicationsAmount;
    private int subscriptionBillAmount;
    private int subscriptionAmount;

    public void loadUserWindow(User user, Connection connection) {
        try {
            subscriptionList = subscriptionDao.getAll(connection)
                    .stream()
                    .filter(subscription -> subscription.getUsersId() == user.getId())
                    .filter(subscription -> subscription.getSubscriptionStatusId() == 1)//check id
                    .collect(Collectors.toList());
            subscriptionAmount = subscriptionList.size();
            subscriptionBillList = subscriptionBillDao.getAll(connection)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getUserId() == user.getId())
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == 2)//check id
                    .collect(Collectors.toList());
            subscriptionBillAmount = subscriptionBillList.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subscription> selectSubscByStatus (SubscriptionStatus status, User user) {
        Connection connection = ConnectionPool.getConnection();
        subscriptionList = null;
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
