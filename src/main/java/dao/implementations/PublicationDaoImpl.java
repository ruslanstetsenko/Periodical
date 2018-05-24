package dao.implementations;

import beans.Publication;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.PublicationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImpl implements PublicationDao {
    private static final Logger logger = LogManager.getLogger(PublicationDaoImpl.class);

    @Override
    public void create(Publication publication, Connection connection) {
        String sql = "INSERT INTO publication (name, issn_number, registration_date, website, publication_type_id, publication_status_id, publication_theme_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setInt(2, publication.getIssnNumber());
            preparedStatement.setDate(3, publication.getRegistrationDate());
            preparedStatement.setString(4, publication.getWebsite());
            preparedStatement.setInt(5, publication.getPublicationTypeId());
            preparedStatement.setInt(6, publication.getPublicationStatusId());
            preparedStatement.setInt(7, publication.getPublicationThemeId());

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create publication in DB", e.getCause());
        }
    }

    @Override
    public Publication read(int id, Connection connection) {
        Publication publication = new Publication();
        String sql = "select name, issn_number, registration_date, website, publication_type_id, publication_status_id, publication_theme_id from publication where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publication.setId(id);
                publication.setName(resultSet.getString("name"));
                publication.setIssnNumber(resultSet.getInt("issn_number"));
                publication.setRegistrationDate(resultSet.getDate("registration_date"));
                publication.setWebsite(resultSet.getString("website"));
                publication.setPublicationTypeId(resultSet.getInt("publication_type_id"));
                publication.setPublicationStatusId(resultSet.getInt("publication_status_id"));
                publication.setPublicationThemeId(resultSet.getInt("publication_theme_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't read publication from DB", e.getCause());
        }
        return publication;
    }

    @Override
    public int getLastPublicationId(Connection connection) {
        int id = 0;
        String sql = "SELECT LAST_INSERT_ID() AS lastId FROM publication";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            logger.error("Can't get last added publication from DB", e.getCause());
        }
        return id;
    }

    @Override
    public void update(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, Connection connection) {
        String sql = "UPDATE publication SET name=?, issn_number=?, registration_date=?, website=?, publication_type_id=?, publication_status_id=?, publication_theme_id=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pubName);
            preparedStatement.setInt(2, issn);
            preparedStatement.setDate(3, setDate);
            preparedStatement.setString(4, website);
            preparedStatement.setInt(5, publicationType);
            preparedStatement.setInt(6, publicationStatus);
            preparedStatement.setInt(7, publicationTheme);
            preparedStatement.setInt(8, publicationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update publication in DB", e.getCause());
        }

    }

    @Override
    public void delete(int publicationId, Connection connection) {
        String sql = "DELETE FROM publication WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete publication in DB", e.getCause());
        }
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
            logger.error("Can't tet publications from DB", e.getCause());
        }
        return list;
    }

    @Override
    public List<Publication> getByTypeThemeStatus(Connection connection, int typeId, int themeId, int statusId) {
        List<Publication> list = new ArrayList<>();
        String sql = "SELECT * FROM publication WHERE publication_type_id=? AND publication_theme_id=? AND publication_status_id=? ORDER BY publication_status_id ASC";

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
            logger.error("Can't get publication selected by type, theme, status from DB", e.getCause());
        }
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
            logger.error("Can't get publication selected by type from DB", e.getCause());
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
            logger.error("Can't get publication selected by theme from DB", e.getCause());
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
            logger.error("Can't get publication selected by status from DB", e.getCause());
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
            logger.error("Can't get publication selected by thype, theme from DB", e.getCause());
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
            logger.error("Can't get publication selected by type, status from DB", e.getCause());
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
            logger.error("Can't get publication selected by theme, status from DB", e.getCause());
        }
        return list;
    }
}
