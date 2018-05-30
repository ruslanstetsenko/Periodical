package dao.implementations;

import beans.PublicationType;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.PublicationTypeDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationTypeDaoImpl implements PublicationTypeDao {
    private static final Logger logger = LogManager.getLogger(PublicationTypeDaoImpl.class);

    @Override
    public int create(PublicationType publicationType, Connection connection) {
        String sql = "INSERT INTO publication_type (type_name) VALUES ?";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationType.getTypeName());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create publication type in DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
        return id;
    }

    @Override
    public PublicationType read(int id, Connection connection) {
        PublicationType publicationType = new PublicationType();
        String sql = "SELECT type_name FROM publication_type WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publicationType.setId(id);
                publicationType.setTypeName(resultSet.getString("type_name"));
            }
        } catch (SQLException e) {
            logger.error("Can't read publication type from DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
        return publicationType;
    }

    @Override
    public int readByName(Connection connection, String typeName) {
        int id = 0;
        String sql = "SELECT id FROM publication_type WHERE type_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, typeName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Can't read publication type by name from DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
        return id;
    }

    @Override
    public void update(PublicationType publicationType, Connection connection) {
        String sql = "UPDATE publication_type SET type_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationType.getTypeName());
            preparedStatement.setInt(2, publicationType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update publication type in DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
    }

    @Override
    public void delete(PublicationType publicationType, Connection connection) {
        String sql = "DELETE FROM publication_type WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete publication type in DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
    }

    @Override
    public List<PublicationType> getAll(Connection connection) {
        List<PublicationType> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_type ORDER BY id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new PublicationType.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTypeName(resultSet.getString("type_name"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get publication types from DB", e.getCause());
            throw new DataBaseWorkException("message.error.publicationSupport");
        }
        return list;
    }
}
