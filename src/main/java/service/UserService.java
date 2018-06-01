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
import java.time.LocalDate;
import java.util.List;

/**
 * User service. Working with user
 * @author Stetsenko Ruslan
 */
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserDao userDao = DaoFactory.getUserDao();
    private AccountDao accountDao = DaoFactory.getAccountDao();
    private PassportIdentNumberDao passportIdentNumberDao = DaoFactory.getPassportIdentNumberDao();
    private LivingAddressDao livingAddressDao = DaoFactory.getLivingAddressDao();
    private ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();

    /**
     * Get user from database
     * @param id users's id number in database
     * @return user from the database or null
     * @throws DataBaseWorkException errors in DAO layer
     */
    public User read(int id) {
        Connection connection = ConnectionPool.getConnection(true);
        User user = null;
        try {
            user = userDao.read(id, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't read user", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return user;
    }

    /**
     * Create user from database
     * @param userName users's name
     * @param userSurName users's surname
     * @param userLastName user's lastname
     * @param userBirthDate user's birthday
     * @param passportSerial passport's serial
     * @param passportNumber passport's number
     * @param passportDateOfIssue passport's issued date
     * @param passportIssuedBy = who issued passport
     * @param identNuber user's ident number
     * @param region living region
     * @param district living district
     * @param city living city
     * @param street living street
     * @param building living building
     * @param appartment living appartment
     * @param userPhoneNumber user's phone number
     * @param userEmail user's email
     * @param login user's login
     * @param password user's password
     * @return user's id in database or 0
     * @throws DataBaseWorkException errors in DAO layer
     */
    public int createUser(String userName, String userSurName, String userLastName, String userBirthDate, String passportSerial, String passportNumber, String passportDateOfIssue, String passportIssuedBy, String identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail, String login, String password) {
        Connection connection = ConnectionPool.getConnection(false);
        int userId;
        int passportNumber1 = Integer.valueOf(passportNumber);
            Date userBirthDate1 = Date.valueOf(userBirthDate);
            Date userRegistrationDate = Date.valueOf(LocalDate.now());
            Date passportDateOfIssue1 = Date.valueOf(passportDateOfIssue);
    try {
        int passportId = passportIdentNumberDao.create(passportSerial, passportNumber1, passportDateOfIssue1, passportIssuedBy, identNuber, connection);
        int addressId = livingAddressDao.create(region, district, city, street, building, appartment, connection);
        int contactInfoId = contactInfoDao.create(userPhoneNumber, userEmail, connection);
        int accountId = accountDao.create(login, password, connection);
        userId = userDao.create(userName, userSurName, userLastName, userBirthDate1, userRegistrationDate, passportId, 2, addressId, contactInfoId, accountId, connection);

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

    /**
     * Create user from database
     * @param userName users's name
     * @param userSurName users's surname
     * @param userLastName user's lastname
     * @param userBirthDate user's birthday
     * @param passportSerial passport's serial
     * @param passportNumber passport's number
     * @param passportDateOfIssue passport's issued date
     * @param passportIssuedBy = who issued passport
     * @param identNuber user's ident number
     * @param region living region
     * @param district living district
     * @param city living city
     * @param street living street
     * @param building living building
     * @param appartment living appartment
     * @param userPhoneNumber user's phone number
     * @param userEmail user's email
     * @throws DataBaseWorkException errors in DAO layer
     */
    public void updateUser(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail) {
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

    /**
     * Get all users from database
     * @return list with all users from database or null
     * @throws DataBaseWorkException errors in DAO layer
     */
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

    /**
     * Get all user's info
     * @see AboutUserWrapper
     * @return support wrapper with all information about user or null
     * @throws DataBaseWorkException errors in DAO layer
     */
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
