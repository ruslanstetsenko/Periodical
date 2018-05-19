package dao;

import beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user, Connection connection) {
        String sql = "INSERT INTO users (name, surname, last_name, birthday, registration_date, passport_ident_number_id, accounts_id, living_address_id, contact_info_id, user_role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setDate(5, user.getRegistrationDate());
            preparedStatement.setInt(6, user.getPassportIdentNumberId());
            preparedStatement.setInt(7, user.getAccountsId());
            preparedStatement.setInt(8, user.getLivingAddressId());
            preparedStatement.setInt(9, user.getContactInfoId());
            preparedStatement.setInt(10, user.getUserRoleId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User read(int id, Connection connection) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(id);
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLastName(resultSet.getString("last_name"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setRegistrationDate(resultSet.getDate("registration_date"));
            user.setPassportIdentNumberId(resultSet.getInt("passport_ident_number_id"));
            user.setAccountsId(resultSet.getInt("accounts_id"));
            user.setLivingAddressId(resultSet.getInt("living_address_id"));
            user.setContactInfoId(resultSet.getInt("contact_info_id"));
            user.setUserRoleId(resultSet.getInt("user_role_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void update(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name=?, surname=?, last_name=?, birthday=?, registration_date=?, passport_ident_number_id=?, accounts_id=?, living_address_id=?, contact_info_id=?, user_role_id=? WHERE id=?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setDate(4, user.getBirthday());
        preparedStatement.setDate(5, user.getRegistrationDate());
        preparedStatement.setInt(6, user.getPassportIdentNumberId());
        preparedStatement.setInt(7, user.getAccountsId());
        preparedStatement.setInt(8, user.getLivingAddressId());
        preparedStatement.setInt(9, user.getContactInfoId());
        preparedStatement.setInt(10, user.getUserRoleId());
        preparedStatement.setInt(11, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> getAll(Connection connection) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new User.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setLastName(resultSet.getString("last_name"))
                        .setBirthday(resultSet.getDate("birthday"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setPassportIdentNumberId(resultSet.getInt("passport_ident_number_id"))
                        .setAccountsId(resultSet.getInt("accounts_id"))
                        .setLivingAddressId(resultSet.getInt("living_address_id"))
                        .setContactInfoId(resultSet.getInt("contact_info_id"))
                        .setUserRoleId(resultSet.getInt("user_role_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
