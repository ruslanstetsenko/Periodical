import connection.ConnectionPool;
import dao.AccountDao;
import dao.AccountDaoImpl;
import entity.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DbTest {
    public static void main(String[] args) throws SQLException {
        testAccount();


    }

    private static void testAccount() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        AccountDao accountDao = new AccountDaoImpl();
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

        try {
            accountDao.create(account1, connection);
            accountDao.create(account2, connection);
            accountDao.create(account3, connection);
            connection.commit();

            list = accountDao.getAll(connection);

            System.out.println(accountDao.read(list.get(0).getId(), connection));
            System.out.println(accountDao.read(list.get(1).getId(), connection));
            System.out.println(accountDao.read(list.get(2).getId(), connection));

            Account accountUpdate = accountDao.read(list.get(0).getId(), connection);
            accountUpdate.setLogin("123");
            accountUpdate.setPassword("456");
            accountDao.update(accountUpdate, connection);
            connection.commit();

            System.out.println(accountDao.read(list.get(0).getId(), connection));

            Account accountDelete = accountDao.read(list.get(1).getId(), connection);
            accountDao.delete(accountDelete, connection);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }

        for (Account account: list) {
            System.out.println(account);
        }
    }
}
