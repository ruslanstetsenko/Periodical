package service;

import beans.PublicationPeriodicyCost;
import beans.User;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.SubscriptionBillDao;
import beans.SubscriptionBill;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bill service. Working with subscription's bill
 * @author Stetsenko Ruslan
 */
public class SubscriptionBillService {
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionBillService.class);

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

    /**
     * Create an account for subscriptions
     * @param connection to database
     * @param userId current user id number
     * @param publicationPeriodicyCostList list subscriptions' cost
     * @return bill's id number
     * @throws DataBaseWorkException errors in DAO layer
     */
    public int createBill(Connection connection, int userId, List<PublicationPeriodicyCost> publicationPeriodicyCostList) {
        SubscriptionBill subscriptionBill = new SubscriptionBill();
        subscriptionBill.setUserId(userId);
        subscriptionBill.setTotalCost(publicationPeriodicyCostList.stream().mapToDouble(PublicationPeriodicyCost::getCost).sum());
        subscriptionBill.setBillNumber((new Date().getTime()) % 1_000_000 + "_" + userId);
        int subscriptionBillId;
        try {
            subscriptionBillId = subscriptionBillDao.create(subscriptionBill, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't create bill", e.getCause());
            throw e;
        }
        return subscriptionBillId;
    }

    /**
     * Get bill from database
     * @param billId bill's id number
     * @return bill from database or null
     * @throws DataBaseWorkException errors in DAO layer
     */
    public SubscriptionBill getBill(int billId) {
        Connection connection = ConnectionPool.getConnection(true);
        SubscriptionBill bill = null;
        try {
            bill = subscriptionBillDao.read(billId, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get bill", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return bill;
    }

    /**
     * Get selected bills by paid status
     * @param status paid status
     * @return list with selected bills
     * @throws DataBaseWorkException errors in DAO layer
     */
    public List<SubscriptionBill> selectBillsByStatus(int status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList = null;
        try {
            if (status == 0) {
                subscriptionBillList = subscriptionBillDao.getAll(connection);
            } else {
                subscriptionBillList = subscriptionBillDao.getByStatus(connection, status);
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't select bills", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return subscriptionBillList;
    }

    /**
     * Get selected bills by paid status and user
     * @param status paid status
     * @param userId user's id number
     * @return list with selected bills
     * @throws DataBaseWorkException errors in DAO layer
     */
    public List<SubscriptionBill> selectBillsByUserByStatus(int userId, int status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList;
        try {
            if (status == 0) {
                subscriptionBillList = subscriptionBillDao.getByUser(connection, userId);
            } else {
                subscriptionBillList = subscriptionBillDao.getByUser(connection, userId)
                        .stream()
                        .filter(subscriptionBill -> subscriptionBill.getPaid() == status)
                        .collect(Collectors.toList());
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't select bills", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return subscriptionBillList;
    }

    /**
     * Get selected bills by paid status with information about user
     * @param status paid status
     * @return map with selected bills and users
     * @throws DataBaseWorkException errors in DAO layer
     */
    public Map<SubscriptionBill, User> getBillWithUsersByStatus(int status) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<SubscriptionBill, User> map = null;
        try {
            map = subscriptionBillDao.getBillWithUsersByStatus(connection, status);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't select bills", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return map;
    }
}
