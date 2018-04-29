package dao;

import entity.PublicationStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationStatusDaoImpl implements PublicationStatusDao {

    @Override
    public void create(PublicationStatus publicationStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publication_status (status_name) VALUE ?");
        preparedStatement.setString(1, publicationStatus.getStatusName());
        preparedStatement.execute();
    }

    @Override
    public PublicationStatus read(int id, Connection connection) throws SQLException {
        PublicationStatus publicationStatus = new PublicationStatus();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT status_name FROM publication_status WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        publicationStatus.setId(id);
        publicationStatus.setStatusName(resultSet.getString("status_name"));

        return publicationStatus;
    }

    @Override
    public void update(PublicationStatus publicationStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publication_status SET status_name=? WHERE id=?");
        preparedStatement.setString(1, publicationStatus.getStatusName());
        preparedStatement.setInt(2, publicationStatus.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(PublicationStatus publicationStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publication_status WHERE id=?");
        preparedStatement.setInt(1, publicationStatus.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<PublicationStatus> getAll(Connection connection) throws SQLException {
        List<PublicationStatus> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM publication_status ORDER BY id");

        while (resultSet.next()) {
            list.add(new PublicationStatus.Builder()
                    .setId(resultSet.getInt("id"))
                    .setStatusName(resultSet.getString("status_name"))
                    .build());
        }

        return list;
    }
}
