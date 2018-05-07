package dao;

import beens.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, surname, last_name, birthday, registration_date, passport_ident_number_id, accounts_id, living_address_id, contact_info_id, user_role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
    }

    @Override
    public User read(int id, Connection connection) throws SQLException {
        User user = new User();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, surname, last_name, birthday, registration_date, passport_ident_number_id, accounts_id, living_address_id, contact_info_id, user_role_id FROM users WHERE id=?");
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
    public List<User> getAll(Connection connection) throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users ORDER BY id");

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
        return list;
    }
}
