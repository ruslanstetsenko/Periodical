package dao;

import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public void create(User user, Connection connection) throws SQLException;
    public User read(int id, Connection connection) throws SQLException;
    public void update(User user, Connection connection) throws SQLException;
    public void delete(User user, Connection connection) throws SQLException;
    public List<User> getAll(Connection connection) throws SQLException;

}
