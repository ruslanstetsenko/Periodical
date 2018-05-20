package dao;

import beans.Publication;

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
    public void update(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publication SET name=?, issn_number=?, registration_date=?, website=?, publication_type_id=?, publication_status_id=?, publication_theme_id=? WHERE id=?");
        preparedStatement.setString(1, pubName);
        preparedStatement.setInt(2, issn);
        preparedStatement.setDate(3, setDate);
        preparedStatement.setString(4, website);
        preparedStatement.setInt(5, publicationType);
        preparedStatement.setInt(6, publicationStatus);
        preparedStatement.setInt(7, publicationTheme);
        preparedStatement.setInt(8, publicationId);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int publicationId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publication WHERE id=?");
        preparedStatement.setInt(1, publicationId);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Publication> getAll(Connection connection) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication ORDER BY publication_status_id ASC";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByTypeThemeStatus(Connection connection, int typeId, int themeId, int statusId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_type_id=? AND publication_theme_id=? AND publication_status_id=? ORDER BY publication_status_id ASC";
//        System.out.println("typeId = " + typeId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, themeId);
            preparedStatement.setInt(3, statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(typeId)
                        .setPublicationStatusId(statusId)
                        .setPublicationThemeId(themeId)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }

    @Override
    public List<Publication> getByType(Connection connection, int typeId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_type_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, typeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(typeId)
                        .setPublicationStatusId(resultSet.getInt("publication_status_id"))
                        .setPublicationThemeId(resultSet.getInt("publication_theme_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByTheme(Connection connection, int themeId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_theme_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, themeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(resultSet.getInt("publication_type_id"))
                        .setPublicationStatusId(resultSet.getInt("publication_status_id"))
                        .setPublicationThemeId(themeId)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByStatus(Connection connection, int statusId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_status_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(resultSet.getInt("publication_type_id"))
                        .setPublicationStatusId(statusId)
                        .setPublicationThemeId(resultSet.getInt("publication_theme_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByTypeByTheme(Connection connection, int typeId, int themeId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_type_id=? AND publication_theme_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, themeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(typeId)
                        .setPublicationStatusId(resultSet.getInt("publication_status_id"))
                        .setPublicationThemeId(themeId)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByTypeByStatus(Connection connection, int typeId, int statusId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_type_id=? AND publication_status_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(typeId)
                        .setPublicationStatusId(statusId)
                        .setPublicationThemeId(resultSet.getInt("publication_theme_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Publication> getByThemeByStatus(Connection connection, int themeId, int statusId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_theme_id=? AND publication_status_id=? ORDER BY publication_status_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, themeId);
            preparedStatement.setInt(2, statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Publication.Builder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIssnNumber(resultSet.getInt("issn_number"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setWebsite(resultSet.getString("website"))
                        .setPublicationTypeId(resultSet.getInt("publication_type_id"))
                        .setPublicationStatusId(statusId)
                        .setPublicationThemeId(themeId)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
