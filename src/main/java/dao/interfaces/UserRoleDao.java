package dao.interfaces;

import beans.UserRole;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRoleDao {

    int create(UserRole userRole, Connection connection) throws DataBaseWorkException;
    UserRole read(int id, Connection connection) throws DataBaseWorkException;
    void update(UserRole userRole, Connection connection) throws DataBaseWorkException;
    void delete(UserRole userRole, Connection connection) throws DataBaseWorkException;
    List<UserRole> getAll(Connection connection) throws DataBaseWorkException;
}
