package dao.interfaces;

import beans.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void create(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, int passportId, int userRoleId, int addressId, int contactInfoId, int accountId, Connection connection);
    User read(int id, Connection connection);
    void update(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, Connection connection);
    void delete(User user, Connection connection);
    List<User> getAll(Connection connection);
    int getLastId(Connection connection);

}
