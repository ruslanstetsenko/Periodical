package dao;

import beans.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public void create(User user, Connection connection);
    public User read(int id, Connection connection);
    public void update(User user, Connection connection) throws SQLException;
    public void delete(User user, Connection connection) throws SQLException;
    public List<User> getAll(Connection connection);

}
