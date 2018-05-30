package dao.implementations;

import beans.ContactInfo;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.ContactInfoDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoDaoImpl implements ContactInfoDao {
    private static final Logger logger = LogManager.getLogger(ContactInfoDaoImpl.class);

    @Override
    public int create(String userPhoneNumber, String userEmail, Connection connection) {
        String sql = "INSERT INTO contact_info (phone, email) VALUES (?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userPhoneNumber);
            preparedStatement.setString(2, userEmail);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create user contact info in DB", e.getCause());
            throw new DataBaseWorkException("message.error.contactInfo");
        }
        return id;
    }

    @Override
    public ContactInfo read(int id, Connection connection) {
        ContactInfo contactInfo = new ContactInfo();
        String sql = "select phone, email from contact_info where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contactInfo.setId(id);
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            logger.error("Can't read user contact info  from DB", e.getCause());
            throw new DataBaseWorkException("message.error.contactInfo");
        }

        return contactInfo;
    }

    @Override
    public void update(int contactInfoId, String userPhoneNumber, String userEmail, Connection connection) {
        String sql = "UPDATE contact_info SET phone=?, email=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userPhoneNumber);
            preparedStatement.setString(2, userEmail);
            preparedStatement.setInt(3, contactInfoId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user contact info in DB", e.getCause());
            e.printStackTrace();
            throw new DataBaseWorkException("message.error.contactInfo");
        }
    }

    @Override
    public void delete(ContactInfo contactInfo, Connection connection) {
        String sql = "DELETE FROM contact_info WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, contactInfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user contact info in DB", e.getCause());
            throw new DataBaseWorkException("message.error.contactInfo");
        }
    }

    @Override
    public List<ContactInfo> getAll(Connection connection) {
        List<ContactInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM contact_info ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new ContactInfo.Builder()
                        .setId(resultSet.getInt("id"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get user contacts info from DB", e.getCause());
            throw new DataBaseWorkException("message.error.contactInfo");
        }
        return list;
    }

//    @Override
//    public int getLastId(Connection connection) {
//        int id = 0;
//        String sql = "SELECT LAST_INSERT_ID() AS lastId FROM contact_info";
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(sql);
//            if (resultSet.next()) {
//                id = resultSet.getInt("lastId");
//            }
//        } catch (SQLException e) {
//            logger.error("Can't read last added user contact info from DB", e.getCause());
//        }
//        return id;
//    }
}
