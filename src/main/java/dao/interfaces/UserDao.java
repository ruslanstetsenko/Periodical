package dao.interfaces;

import beans.User;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    int create(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, int passportId, int userRoleId, int addressId, int contactInfoId, int accountId, Connection connection) throws DataBaseWorkException;
    User read(int id, Connection connection) throws DataBaseWorkException;
    void update(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, Connection connection) throws DataBaseWorkException;
    void delete(User user, Connection connection) throws DataBaseWorkException;
    List<User> getAll(Connection connection) throws DataBaseWorkException;
    int getLastId(Connection connection) throws DataBaseWorkException;

}
