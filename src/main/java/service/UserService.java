package service;

import beans.*;
import connection.ConnectionPool;
import dao.*;
import dao.interfaces.*;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wrappers.AboutUserWrapper;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserDao userDao = DaoFactory.getUserDao();
    private AccountDao accountDao = DaoFactory.getAccountDao();
    private PassportIdentNumberDao passportIdentNumberDao = DaoFactory.getPassportIdentNumberDao();
    private LivingAddressDao livingAddressDao = DaoFactory.getLivingAddressDao();
    private ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();

    public User read(int userId) {
        Connection connection = ConnectionPool.getConnection(true);
        User user = null;
        try {
            user = userDao.read(userId, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't read user", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return user;
    }

    public int createUser(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail, String login, String password) {
        Connection connection = ConnectionPool.getConnection(false);
        int userId;
    try {
        int passportId = passportIdentNumberDao.create(passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, connection);
        int addressId = livingAddressDao.create(region, district, city, street, building, appartment, connection);
        int contactInfoId = contactInfoDao.create(userPhoneNumber, userEmail, connection);
        int accountId = accountDao.create(login, password, connection);
        userId = userDao.create(userName, userSurName, userLastName, userBirthDate, userRegistrationDate, passportId, 2, addressId, contactInfoId, accountId, connection);

        ConnectionPool.commitTransaction(connection);
    } catch (DataBaseWorkException e) {
        ConnectionPool.transactionRollback(connection);
        LOGGER.error("Can't create user", e.getCause());
        throw e;
    } finally {
        ConnectionPool.closeConnection(connection);
    }
        return userId;
    }

    public void updateUser(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail, String login, String password) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            User user = userDao.read(userId, connection);
            passportIdentNumberDao.update(user.getPassportIdentNumberId(), passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, connection);
            livingAddressDao.update(user.getLivingAddressId(), region, district, city, street, building, appartment, connection);
            contactInfoDao.update(user.getContactInfoId(), userPhoneNumber, userEmail, connection);
            userDao.update(userId, userName, userSurName, userLastName, userBirthDate, connection);
            ConnectionPool.commitTransaction(connection);
        } catch (DataBaseWorkException e) {
            ConnectionPool.transactionRollback(connection);
            LOGGER.error("Can't update user", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public List<User> getAllUsers() {
        Connection connection = ConnectionPool.getConnection(true);
        List<User> list = null;
        try {
            list = userDao.getAll(connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get users", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return list;
    }

    public AboutUserWrapper getUserInfo(int userId) {
        Connection connection = ConnectionPool.getConnection(true);
        AboutUserWrapper wrapper = null;
        try {
            User user = userDao.read(userId, connection);
            Account account = accountDao.read(user.getAccountsId(), connection);
            PassportIdentNumber passportIdentNumber = passportIdentNumberDao.read(user.getPassportIdentNumberId(), connection);
            LivingAddress livingAddress = livingAddressDao.read(user.getLivingAddressId(), connection);
            ContactInfo contactInfo = contactInfoDao.read(user.getContactInfoId(), connection);
            wrapper = new AboutUserWrapper(account, passportIdentNumber, livingAddress, contactInfo, user);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get user info", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }
}
