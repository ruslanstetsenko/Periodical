package dao.implementations;

import beans.ContactInfo;
import dao.interfaces.ContactInfoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoDaoImpl implements ContactInfoDao {

    @Override
    public void create(String userPhoneNumber, String userEmail, Connection connection) {
        String sql = "INSERT INTO contact_info (phone, email) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userPhoneNumber);
            preparedStatement.setString(2, userEmail);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ContactInfo read(int id, Connection connection) {
        ContactInfo contactInfo = new ContactInfo();
        String sql = "select phone, email from contact_info where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())  {
                contactInfo.setId(id);
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ContactInfo contactInfo, Connection connection) {
        String sql = "DELETE FROM contact_info WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, contactInfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        String sql = "SELECT LAST_INSERT_ID() AS lastId FROM contact_info";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
