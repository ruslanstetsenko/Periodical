package dao;

import beans.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public void create(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, int passportId, int userRoleId, int addressId, int contactInfoId, int accountId, Connection connection);
    public User read(int id, Connection connection);
    public void update(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, Connection connection);
    public void delete(User user, Connection connection) throws SQLException;
    public List<User> getAll(Connection connection);
    public int getLastId(Connection connection);

}
