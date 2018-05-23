package dao.implementations;

import beans.PublicationStatus;
import dao.interfaces.PublicationStatusDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationStatusDaoImpl implements PublicationStatusDao {

    @Override
    public void create(PublicationStatus publicationStatus, Connection connection) {
        String sql = "INSERT INTO publication_status (status_name) VALUE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationStatus.getStatusName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PublicationStatus read(int id, Connection connection) {
        PublicationStatus publicationStatus = new PublicationStatus();
        String sql = "SELECT status_name FROM publication_status WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publicationStatus.setId(id);
                publicationStatus.setStatusName(resultSet.getString("status_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicationStatus;
    }

    @Override
    public int readByName(Connection connection, String statusName) {
        int id = 0;
        String sql = "SELECT id FROM publication_status WHERE status_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, statusName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(PublicationStatus publicationStatus, Connection connection) {
        String sql = "UPDATE publication_status SET status_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationStatus.getStatusName());
            preparedStatement.setInt(2, publicationStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PublicationStatus publicationStatus, Connection connection) {
        String sql = "DELETE FROM publication_status WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<PublicationStatus> getAll(Connection connection) {
        List<PublicationStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_status ORDER BY id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new PublicationStatus.Builder()
                        .setId(resultSet.getInt("id"))
                        .setStatusName(resultSet.getString("status_name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
