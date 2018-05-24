import beans.ContactInfo;
import beans.Publication;
import beans.PublicationPeriodicyCost;
import connection.ConnectionPool;
import dao.*;
import beans.Account;
import dao.interfaces.*;
import service.PublicationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbTest {

    public static void main(String[] args) throws SQLException {
//        testAccount();
//        testContactInfo();
//        testSubscription();
//        testPublicationType();
//        testPublicationTheme();
//        testPublication();
        testPublicService();
//        testPubPeriodCost();
    }

    private static void testAccount() throws SQLException {
        Connection connection = ConnectionPool.getConnection(true);
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> list = accountDao.getAll(connection);

        Account account1 = new Account();
        account1.setLogin("login1");
        account1.setPassword("password1");

        Account account2 = new Account();
        account2.setLogin("login2");
        account2.setPassword("password2");

        Account account3 = new Account();
        account3.setLogin("login3");
        account3.setPassword("password3");

//        try {
//            accountDao.create(account1, connection);
//            accountDao.create(account2, connection);
//            accountDao.create(account3, connection);
//            connection.commit();
//
//            list = accountDao.getAll(connection);
//
//            System.out.println(accountDao.read(list.get(0).getId(), connection));
//            System.out.println(accountDao.read(list.get(1).getId(), connection));
//            System.out.println(accountDao.read(list.get(2).getId(), connection));
//
//            Account accountUpdate = accountDao.read(list.get(0).getId(), connection);
//            accountUpdate.setLogin("123");
//            accountUpdate.setPassword("456");
//            accountDao.update(accountUpdate, connection);
//            connection.commit();
//
//            System.out.println(accountDao.read(list.get(0).getId(), connection));
//
//            Account accountDelete = accountDao.read(list.get(1).getId(), connection);
//            accountDao.delete(accountDelete, connection);
//            connection.commit();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//        } finally {
//            connection.close();
//        }
//
//        for (Account account : list) {
//            System.out.println(account);
//        }
    }

    private static void testContactInfo() throws SQLException {
        Connection connection = ConnectionPool.getConnection(false);
        ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();
        List<ContactInfo> list = contactInfoDao.getAll(connection);

        ContactInfo contactInfo1 = new ContactInfo();
        contactInfo1.setPhone("063 292 44 45");
        contactInfo1.setEmail("r.stetsenko@gmail.com");

        ContactInfo contactInfo2 = new ContactInfo();
        contactInfo2.setPhone("096 834 40 66");
        contactInfo2.setEmail("arslan@ukr.net");

        ContactInfo contactInfo3 = new ContactInfo();
        contactInfo3.setPhone("044 234 32 32");
        contactInfo3.setEmail("gergb@ukr.net");

//        try {
            contactInfoDao.create("063 292 44 45", "arslan@ukr.net", connection);
            contactInfoDao.create("096 834 40 66", "r.stetsenko@gmail.com", connection);
            contactInfoDao.create("044 234 32 32", "gergb@ukr.net", connection);
            connection.commit();

            list = contactInfoDao.getAll(connection);

            System.out.println(contactInfoDao.read(list.get(0).getId(), connection));
            System.out.println(contactInfoDao.read(list.get(1).getId(), connection));
            System.out.println(contactInfoDao.read(list.get(2).getId(), connection));

//            ContactInfo contactInfoUpdate = contactInfoDao.read(list.get(0).getId(), connection);
//            contactInfoUpdate.setEmail("123@123");
//            contactInfoUpdate.setPhone("0000000000");
//            contactInfoDao.update(contactInfoUpdate, connection);
//            connection.commit();
//
//            System.out.println(contactInfoDao.read(list.get(0).getId(), connection));
//
//            ContactInfo contactInfoDelete = contactInfoDao.read(list.get(1).getId(), connection);
//            contactInfoDao.delete(contactInfoDelete, connection);
//            connection.commit();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//        } finally {
//            connection.close();
//        }
//
//        for (ContactInfo contactInfo : list) {
//            System.out.println(contactInfo);
//        }
    }

    private static void testSubscription() throws SQLException {
        Connection connection = ConnectionPool.getConnection(true);
        SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
        System.out.println(subscriptionDao.getSubscByBillByUser(connection, 2, 1));
        System.out.println();
        System.out.println(subscriptionDao.getSubscByBill(connection, 4));
    }

    private static void testPublicationType() throws SQLException{
        Connection connection = ConnectionPool.getConnection(true);
        PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
        System.out.println(publicationTypeDao.read(4, connection));
    }

    private static void testPublicationTheme() throws SQLException {
        Connection connection = ConnectionPool.getConnection(true);
        PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
        System.out.println(publicationThemeDao.read(5, connection));

    }

    private static void testPublication() throws SQLException {
        Connection connection = ConnectionPool.getConnection(true);
        PublicationDao publicationDao = DaoFactory.getPublicationDao();
        System.out.println(publicationDao.getByTypeThemeStatus(connection, 2, 5, 1));
        System.out.println("last pubId = " + publicationDao.getLastPublicationId(connection));
    }

    private static void testPublicService() {
        Map<Publication, List<PublicationPeriodicyCost>> map = new PublicationService().getPublicationWithCosts(1, 0, 1);
        System.out.println(map);
        System.out.println("amount: " + map.size());

    }

    private static void testPubPeriodCost() throws SQLException {
        Connection connection = ConnectionPool.getConnection(true);
        PublicationPeriodicityCostDao dao = DaoFactory.getPublicationPeriodicityCostDao();
        System.out.println(dao.read(30, connection));
    }

}
