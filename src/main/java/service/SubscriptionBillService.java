package service;

import beans.PublicationPeriodicyCost;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.SubscriptionBillDao;
import beans.SubscriptionBill;
import dao.interfaces.SubscriptionDao;
import dao.interfaces.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionBillService {

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();


    //create biil and subscription
    public int createBill(Connection connection, int userId, List<PublicationPeriodicyCost> publicationPeriodicyCostList) {
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        subscriptionBill.setUserId(userId);
        subscriptionBill.setTotalCost(publicationPeriodicyCostList.stream().mapToDouble(PublicationPeriodicyCost::getCost).sum());
        subscriptionBill.setBillNumber((new Date().getTime()) % 1_000_000 + "_" + userId);
        int subscriptionBillId;
        subscriptionBillDao.create(subscriptionBill, connection);
//            connection.commit();
        subscriptionBillId = subscriptionBillDao.readLastId(connection);
        return subscriptionBillId;
    }

    public SubscriptionBill getBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        SubscriptionBill bill = new SubscriptionBill();
        bill = subscriptionBillDao.read(billId, connection);

        ConnectionPool.closeConnection(connection);
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

        ConnectionPool.closeConnection(connection);
        return subscriptionBillList;
    }

    public List<SubscriptionBill> selectBillsByUserByStatus(int userId, int status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList;
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

//    public void showAboutSubscrBill(SubscriptionBill subscriptionBill) {
//        Connection connection = ConnectionPool.getConnection(true);
//
//        int userId = subscriptionDao.readByBill(subscriptionBill.getId(), connection).getUsersId();
//        User user = userDao.read(userId, connection);
//        ConnectionPool.closeConnection(connection);
//    }

//    public void deleteSubscriptionBill(SubscriptionBill subscriptionBill, int currentBillStatus) {
//        LocalDate setBill = LocalDate.ofInstant(subscriptionBill.getBillSetDay().toInstant(), ZoneId.systemDefault());
//        if (subscriptionBill.getValidityPeriod() >
//                Period.between(setBill, LocalDate.now()).getDays()) {
//            Connection connection = ConnectionPool.getConnection(false);
//            List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
//            try {
//                subscriptionBillDao.delete(subscriptionBill.getId(), connection);
//                connection.commit();
//                subscriptionBillList = subscriptionBillDao.getAll(connection)
//                        .stream()
//                        .filter(subscriptionBill1 -> subscriptionBill1.getPaid() == currentBillStatus)
//                        .collect(Collectors.toList());
////                publicationsAmount = publicationList.size();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            } finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            //cant delete
//        }
//
//    }
}
