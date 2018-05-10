package dao;

import beens.Publication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImpl implements PublicationDao {

    @Override
    public void create(Publication publication, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publication (name, issn_number, registration_date, website, publication_type_id, publication_status_id, publication_theme_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, publication.getName());
        preparedStatement.setInt(2, publication.getIssnNumber());
        preparedStatement.setDate(3, publication.getRegistrationDate());
        preparedStatement.setString(4, publication.getWebsite());
        preparedStatement.setInt(5, publication.getPublicationTypeId());
        preparedStatement.setInt(6, publication.getPublicationStatusId());
        preparedStatement.setInt(7, publication.getPublicationThemeId());

        preparedStatement.execute();
    }

    @Override
    public Publication read(int id, Connection connection) throws SQLException {
        Publication publication = new Publication();

        PreparedStatement preparedStatement = connection.prepareStatement("select name, issn_number, registration_date, website, publication_type_id, publication_status_id, publication_theme_id from publication where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        publication.setId(id);
        publication.setName(resultSet.getString("name"));
        publication.setIssnNumber(resultSet.getInt("issn_number"));
        publication.setRegistrationDate(resultSet.getDate("registration_date"));
        publication.setWebsite(resultSet.getString("website"));
        publication.setPublicationTypeId(resultSet.getInt("publication_type_id"));
        publication.setPublicationStatusId(resultSet.getInt("publication_status_id"));
        publication.setPublicationThemeId(resultSet.getInt("publication_theme_id"));

        return publication;
    }

    @Override
    public int getLastPublicationId(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM publication");
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public void update(Publication publication, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publication SET name=?, issn_number=?, registration_date=?, website=?, publication_type_id=?, publication_status_id=?, publication_theme_id=? WHERE id=?");
        preparedStatement.setString(1, publication.getName());
        preparedStatement.setInt(2, publication.getIssnNumber());
        preparedStatement.setDate(3, publication.getRegistrationDate());
        preparedStatement.setString(4, publication.getWebsite());
        preparedStatement.setInt(5, publication.getPublicationTypeId());
        preparedStatement.setInt(6, publication.getPublicationStatusId());
        preparedStatement.setInt(7, publication.getPublicationThemeId());
        preparedStatement.setInt(8, publication.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int publicationId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publication WHERE id=?");
        preparedStatement.setInt(1, publicationId);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Publication> getAll(Connection connection) throws SQLException {
        List<Publication> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM publication ORDER BY id");

        while (resultSet.next()) {
            list.add(new Publication.Builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setIssnNumber(resultSet.getInt("issn_number"))
                    .setRegistrationDate(resultSet.getDate("registration_date"))
                    .setWebsite(resultSet.getString("website"))
                    .setPublicationTypeId(resultSet.getInt("publication_type_id"))
                    .setPublicationStatusId(resultSet.getInt("publication_status_id"))
                    .setPublicationThemeId(resultSet.getInt("publication_theme_id"))
                    .build());
        }
        return list;
    }
}
