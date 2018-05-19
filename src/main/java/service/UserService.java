package service;

import beans.*;
import connection.ConnectionPool;
import dao.*;
import wrappers.AboutUserWrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDao userDao = DaoFactory.getUserDao();
    private AccountDao accountDao = DaoFactory.getAccountDao();
    private PassportIdentNumberDao passportIdentNumberDao = DaoFactory.getPassportIdentNumberDao();
    private LivingAddressDao livingAddressDao = DaoFactory.getLivingAddressDao();
    private ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();

    public void createUser(User user) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            userDao.create(user, connection);
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

    public List<User> getAllUsers() {
        Connection connection = ConnectionPool.getConnection(true);
        List<User> list = userDao.getAll(connection);
        ConnectionPool.closeConnection(connection);
        return list;
    }

    public AboutUserWrapper getUserInfo(int userId) {
        Connection connection = ConnectionPool.getConnection(true);
        User user = userDao.read(userId, connection);
        Account account = accountDao.read(user.getAccountsId(), connection);
        PassportIdentNumber passportIdentNumber = passportIdentNumberDao.read(user.getPassportIdentNumberId(), connection);
        LivingAddress livingAddress = livingAddressDao.read(user.getLivingAddressId(), connection);
        ContactInfo contactInfo = contactInfoDao.read(user.getContactInfoId(), connection);
        ConnectionPool.closeConnection(connection);

        return new AboutUserWrapper(account, passportIdentNumber, livingAddress, contactInfo, user);
    }
}
