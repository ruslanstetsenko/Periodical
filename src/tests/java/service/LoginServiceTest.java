package service;

import beans.Account;
import beans.User;
import dao.interfaces.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    Connection connection;
    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private LoginService loginServiceMoc = new LoginService();

    private Account account = new Account.Builder().setId(1).setLogin("login").setPassword("password").build();
    private Account account1 = new Account.Builder().setId(2).setLogin("login1").setPassword("password1").build();
    private Account account2 = new Account.Builder().setId(3).setLogin("login2").setPassword("password2").build();
    private Account account3 = new Account.Builder().setId(4).setLogin("login3").setPassword("password3").build();

    private User user = new User.Builder().setId(1).setAccountsId(1).build();
    private User user2 = new User.Builder().setId(2).setAccountsId(2).build();
    private User user3 = new User.Builder().setId(3).setAccountsId(3).build();

    @Before
    public void setUp() throws Exception {
        List<Account> list = new ArrayList<>();
        list.add(account);
        list.add(account1);
        list.add(account2);
        list.add(account3);
        when(accountDao.getAll((connection))).thenReturn(list);
    }

    @Test
    public void checkAccount() {
//        List<Account> list1 = accountDao.getAll(connectionPool2.getConnection(true));
//        when(accountDaoMoc.getAll(connection)).thenReturn(Arrays.asList(account, account1));

//        verify(accountDaoMoc).getAll(connection);
//        assertNotEquals(list, list1);

//        when(loginServiceMoc.checkAccount("login", "password")).thenReturn(account);
        Account accountTest = loginServiceMoc.checkAccount("login", "password");
//        verify(loginServiceMoc).checkAccount("login", "password");
        assertEquals(account, accountTest);
        assertNotEquals(account, account1);
        accountTest = loginServiceMoc.checkAccount(account2.getLogin(), account2.getPassword());
        assertNull(accountTest);
    }

    @Test
    public void checkLogin() {
//        when(loginServiceMoc.checkLogin("login")).thenReturn(true);
        boolean checkTrue = loginServiceMoc.checkLogin("login");
//        verify(loginServiceMoc).checkLogin("login");
        boolean checkFalse = loginServiceMoc.checkLogin("login12");
        assertTrue(checkTrue);
        assertFalse(checkFalse);
    }

    @Test
    public void getUser() {
//        when(loginServiceMoc.getUser(account)).thenReturn(user);
        User userTest = loginServiceMoc.getUser(account);
//        verify(loginServiceMoc).getUser(account);
        assertEquals(user, userTest);
        assertNotEquals(user2, userTest);
        userTest = loginServiceMoc.getUser(account3);
        assertNull(userTest);
    }
}