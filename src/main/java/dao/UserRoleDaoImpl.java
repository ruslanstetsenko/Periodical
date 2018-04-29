package dao;

import entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {

    @Override
    public void create(UserRole userRole, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_role (role_name) VALUE?");
        preparedStatement.setString(1, userRole.getRoleName());
        preparedStatement.execute();
    }

    @Override
    public UserRole read(int id, Connection connection) throws SQLException {
        UserRole userRole = new UserRole();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT role_name FROM user_role WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        userRole.setId(id);
        userRole.setRoleName(resultSet.getString("role_name"));

        return userRole;
    }

    @Override
    public void update(UserRole userRole, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_role SET role_name=? WHERE id=?");
        preparedStatement.setString(1, userRole.getRoleName());
        preparedStatement.setInt(2, userRole.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(UserRole userRole, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_role WHERE id=?");
        preparedStatement.setInt(1, userRole.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<UserRole> getAll(Connection connection) throws SQLException {
        List<UserRole> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_role ORDER BY id");

        while (resultSet.next()) {
            list.add(new UserRole.Builder()
                    .setId(resultSet.getInt("id"))
                    .setRoleName(resultSet.getString("role_name"))
                    .build());
        }
        return list;
    }
}
