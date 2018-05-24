package dao.interfaces;

import beans.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRoleDao {

    void create(UserRole userRole, Connection connection);
    UserRole read(int id, Connection connection);
    void update(UserRole userRole, Connection connection);
    void delete(UserRole userRole, Connection connection);
    List<UserRole> getAll(Connection connection);
}
