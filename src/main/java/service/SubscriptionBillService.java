package service;

import beans.PublicationPeriodicityCost;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.SubscriptionBillDao;
import beans.SubscriptionBill;
import beans.User;
import dao.SubscriptionDao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionBillService {

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();


    //create biil and subscription
    public int createBill(int userId, List<PublicationPeriodicityCost> publicationPeriodicityCostList) {
        Connection connection = ConnectionPool.getConnection(false);
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        subscriptionBill.setUserId(userId);
        subscriptionBill.setTotalCost(publicationPeriodicityCostList.stream().mapToDouble(PublicationPeriodicityCost::getCost).sum());
        subscriptionBill.setBillNumber((new Date().getTime()) % 1_000_000 + "_" + userId);
        int subscriptionBillId = 0;
        try {
            subscriptionBillDao.create(subscriptionBill, connection);
            connection.commit();
            subscriptionBillId = subscriptionBillDao.readLastId(connection);
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
        return subscriptionBillId;
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

    public List<SubscriptionBill> selectBillsByStatus(int status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList;
        if (status == 0) {
            subscriptionBillList = subscriptionBillDao.getAll(connection);
        } else {
            subscriptionBillList = subscriptionBillDao.getByStatus(connection, status);
        }
        return subscriptionBillList;
    }

    public List<SubscriptionBill> selectBillsByUserByStatus(int userId, int status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        if (status == 0) {
            subscriptionBillList = subscriptionBillDao.getByUser(connection, userId);
        } else {
            subscriptionBillList = subscriptionBillDao.getByUser(connection, userId)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == status)
                    .collect(Collectors.toList());
        }

        ConnectionPool.closeConnection(connection);
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
