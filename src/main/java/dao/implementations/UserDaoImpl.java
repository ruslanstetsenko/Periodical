package dao.implementations;

import beans.User;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void create(String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, int passportId, int userRoleId, int addressId, int contactInfoId, int accountId, Connection connection) {
        String sql = "INSERT INTO users (name, surname, last_name, birthday, registration_date, passport_ident_number_id, living_address_id, contact_info_id, user_role_id, accounts_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userSurName);
            preparedStatement.setString(3, userLastName);
            preparedStatement.setDate(4, userBirthDate);
            preparedStatement.setDate(5, userRegistrationDate);
            preparedStatement.setInt(6, passportId);
            preparedStatement.setInt(7, addressId);
            preparedStatement.setInt(8, contactInfoId);
            preparedStatement.setInt(9, userRoleId);
            preparedStatement.setInt(10, accountId);

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create user in DB", e.getCause());
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
            logger.error("Can't read user from DB", e.getCause());
        }

        return user;
    }

    @Override
    public void update(int userId, String userName, String userSurName, String userLastName, Date userBirthDate, Date userRegistrationDate, Connection connection) {
        String sql = "UPDATE users SET name=?, surname=?, last_name=?, birthday=?, registration_date=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userSurName);
            preparedStatement.setString(3, userLastName);
            preparedStatement.setDate(4, userBirthDate);
            preparedStatement.setDate(5, userRegistrationDate);
            preparedStatement.setInt(6, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user in DB", e.getCause());
        }
    }

    @Override
    public void delete(User user, Connection connection) {
        String sql = "DELETE FROM users WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user in DB", e.getCause());
        }
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
            logger.error("Can't get users from DB", e.getCause());
        }
        return list;
    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS lastId FROM users");
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            logger.error("Can't get last added user from DB", e.getCause());
        }
        return id;
    }
}
