package service;

import connection.ConnectionPool;
import dao.*;
import beans.*;
import dao.interfaces.*;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wrappers.LoadUserWindowWrapper;

import java.sql.Connection;
import java.util.*;

public class UserWindowsService {
    private static final Logger LOGGER = LogManager.getLogger(UserWindowsService.class);
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();

    public LoadUserWindowWrapper loadUserWindow(int userId) {
        Connection connection = ConnectionPool.getConnection(true);
        LoadUserWindowWrapper wrapper = null;
        try {
            List<SubscriptionBill> subscriptionBillList = subscriptionBillDao.getByUser(connection, userId);
            Map<String, Subscription> map = subscriptionDao.getSubscByUser(connection, userId);
            wrapper = new LoadUserWindowWrapper.Builder().setMap(map).setSubscriptionBillList(subscriptionBillList).build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't load user window info", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    public Map<String, Subscription> loadSelectedUserWindow(int userId, int currentSubStatusId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<String, Subscription> map = null;
        try {
            if (currentSubStatusId == 0) {
                map = subscriptionDao.getSubscByUser(connection, userId);
            } else {
                map = subscriptionDao.getSubscByStatusByUser(connection, userId, currentSubStatusId);
            }
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't load selected data", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return map;
    }
}
