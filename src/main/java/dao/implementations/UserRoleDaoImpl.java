package dao.implementations;

import beans.UserRole;
import dao.interfaces.UserRoleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    private static final Logger logger = LogManager.getLogger(UserRoleDaoImpl.class);

    @Override
    public void create(UserRole userRole, Connection connection) {
        String sql = "INSERT INTO user_role (role_name) VALUE?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userRole.getRoleName());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create user role in DB", e.getCause());
        }
    }

    @Override
    public UserRole read(int id, Connection connection) {
        UserRole userRole = new UserRole();
        String sql = "SELECT role_name FROM user_role WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userRole.setId(id);
                userRole.setRoleName(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            logger.error("Can't read user role from DB", e.getCause());
        }
        return userRole;
    }

    @Override
    public void update(UserRole userRole, Connection connection) {
        String sql = "UPDATE user_role SET role_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userRole.getRoleName());
            preparedStatement.setInt(2, userRole.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user role in DB", e.getCause());
        }
    }

    @Override
    public void delete(UserRole userRole, Connection connection) {
        String sql = "DELETE FROM user_role WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userRole.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user role in DB", e.getCause());
        }
    }

    @Override
    public List<UserRole> getAll(Connection connection) {
        List<UserRole> list = new ArrayList<>();
        String sql = "SELECT * FROM user_role ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new UserRole.Builder()
                        .setId(resultSet.getInt("id"))
                        .setRoleName(resultSet.getString("role_name"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get user roles from DB", e.getCause());
        }
        return list;
    }
}
