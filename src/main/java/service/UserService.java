package service;

import beans.*;
import connection.ConnectionPool;
import dao.*;
import wrappers.AboutUserWrapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDao userDao = DaoFactory.getUserDao();
    private AccountDao accountDao = DaoFactory.getAccountDao();
    private PassportIdentNumberDao passportIdentNumberDao = DaoFactory.getPassportIdentNumberDao();
    private LivingAddressDao livingAddressDao = DaoFactory.getLivingAddressDao();
    private ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();

    public void createUser(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            passportIdentNumberDao.create(passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, connection);
            int passportId = passportIdentNumberDao.getLastId(connection);

            livingAddressDao.create(region, district, city, street, building, appartment, connection);
            int addressId = livingAddressDao.getLastId(connection);

            contactInfoDao.create(userPhoneNumber, userEmail, connection);
            int contactInfoId = contactInfoDao.getLastId(connection);

            accountDao.create("user", "user", connection);
            int accountId = accountDao.getLastId(connection);

            userDao.create(userName, userSurName, userLastName, userBirthDate, userRegistrationDate, passportId, 2, addressId, contactInfoId, accountId, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        ConnectionPool.closeConnection(connection);
    }

    public void updateUser(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, String region, String district, String city, String street, String building, String appartment, String userPhoneNumber, String userEmail, String login, String password) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            User user = userDao.read(userId, connection);
            passportIdentNumberDao.update(user.getPassportIdentNumberId(), passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, connection);
            livingAddressDao.update(user.getLivingAddressId(), region, district, city, street, building, appartment, connection);
            contactInfoDao.update(user.getContactInfoId(), userPhoneNumber, userEmail, connection);
            accountDao.update(user.getAccountsId(), login, password, connection);
//            connection.commit();
            userDao.update(userId, userName, userSurName, userLastName, userBirthDate, userRegistrationDate, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        ConnectionPool.closeConnection(connection);
    }

    public List<User> getAllUsers() {
        Connection connection = ConnectionPool.getConnection(true);
        List<User> list = userDao.getAll(connection);
        ConnectionPool.closeConnection(connection);
        return list;
    }

    public AboutUserWrapper getUserInfo(int userId) {
        Connection connection = ConnectionPool.getConnection(true);
        User user = userDao.read(userId, connection);
//        if (user.getAccountsId() != null)
        Account account = accountDao.read(user.getAccountsId(), connection);
        PassportIdentNumber passportIdentNumber = passportIdentNumberDao.read(user.getPassportIdentNumberId(), connection);
        LivingAddress livingAddress = livingAddressDao.read(user.getLivingAddressId(), connection);
        ContactInfo contactInfo = contactInfoDao.read(user.getContactInfoId(), connection);
        ConnectionPool.closeConnection(connection);

        return new AboutUserWrapper(account, passportIdentNumber, livingAddress, contactInfo, user);
    }
}
