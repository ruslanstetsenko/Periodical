package service;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.SubscriptionBillDao;
import beens.Subscription;
import beens.SubscriptionBill;
import beens.User;
import dao.SubscriptionDao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionBillService {

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();


    //create biil and subscription
    public void createBill(User user, List<Subscription> subscriptionList) {
        Connection connection = ConnectionPool.getConnection(false);
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

    public SubscriptionBill getBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        SubscriptionBill bill = new SubscriptionBill();
        try {
            bill = subscriptionBillDao.read(billId, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bill;
    }

    public List<SubscriptionBill> selectBillsByStatus(byte status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        subscriptionBillList = null;
        try {
            subscriptionBillList = subscriptionBillDao.getAll(connection)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == status)
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
        return subscriptionBillList;
    }

    public void showAboutSubscrBill(SubscriptionBill subscriptionBill) {
        Connection connection = ConnectionPool.getConnection(true);
        try {
            int userId = subscriptionDao.readByBill(subscriptionBill.getId(), connection).getUsersId();
            User user = userDao.read(userId, connection);
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

    public void deleteSubscriptionBill(SubscriptionBill subscriptionBill, int currentBillStatus) {
        LocalDate setBill = LocalDate.ofInstant(subscriptionBill.getBillSetDay().toInstant(), ZoneId.systemDefault());
        if (subscriptionBill.getValidityPeriod() >
                Period.between(setBill, LocalDate.now()).getDays()) {
            Connection connection = ConnectionPool.getConnection(false);
            List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
            try {
                subscriptionBillDao.delete(subscriptionBill.getId(), connection);
                connection.commit();
                subscriptionBillList = subscriptionBillDao.getAll(connection)
                        .stream()
                        .filter(subscriptionBill1 -> subscriptionBill1.getPaid() == currentBillStatus)
                        .collect(Collectors.toList());
//                publicationsAmount = publicationList.size();
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
        } else {
            //cant delete
        }

    }
}
