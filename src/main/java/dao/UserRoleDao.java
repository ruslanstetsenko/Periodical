package dao;

import beens.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRoleDao {

    public void create(UserRole userRole, Connection connection) throws SQLException;
    public UserRole read(int id, Connection connection) throws SQLException;
    public void update(UserRole userRole, Connection connection) throws SQLException;
    public void delete(UserRole userRole, Connection connection) throws SQLException;
    public List<UserRole> getAll(Connection connection) throws SQLException;

}
