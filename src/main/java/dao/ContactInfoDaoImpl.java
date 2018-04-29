package dao;

import entity.ContactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoDaoImpl implements ContactInfoDao{

    @Override
    public void create(ContactInfo contactInfo, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact_info (phone, email) VALUES (?, ?)");
        preparedStatement.setString(1, contactInfo.getPhone());
        preparedStatement.setString(2, contactInfo.getEmail());
        preparedStatement.execute();
    }

    @Override
    public ContactInfo read(int id, Connection connection) throws SQLException {
        ContactInfo contactInfo = new ContactInfo();

        PreparedStatement preparedStatement = connection.prepareStatement("select phone, email from contact_info where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        contactInfo.setId(id);
        contactInfo.setPhone(resultSet.getString("phone"));
        contactInfo.setEmail(resultSet.getString("email"));

        return contactInfo;
    }

    @Override
    public void update(ContactInfo contactInfo, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact_info SET phone=?, email=? WHERE id=?");
        preparedStatement.setString(1, contactInfo.getPhone());
        preparedStatement.setString(2, contactInfo.getEmail());
        preparedStatement.setInt(3, contactInfo.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(ContactInfo contactInfo, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contact_info WHERE id=?");
        preparedStatement.setInt(1, contactInfo.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<ContactInfo> getAll(Connection connection) throws SQLException {
        List<ContactInfo> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM contact_info ORDER BY id");

        while (resultSet.next()) {
            list.add(new ContactInfo.Builder()
                    .setId(resultSet.getInt("id"))
                    .setPhone(resultSet.getString("phone"))
                    .setEmail(resultSet.getString("email"))
                    .build());
        }
        return list;
    }
}
