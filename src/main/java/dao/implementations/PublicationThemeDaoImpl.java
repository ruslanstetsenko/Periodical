package dao.implementations;

import beans.PublicationTheme;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.PublicationThemeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationThemeDaoImpl implements PublicationThemeDao {
    private static final Logger logger = LogManager.getLogger(PublicationThemeDaoImpl.class);

    @Override
    public void create(PublicationTheme publicationTheme, Connection connection) {
        String sql = "INSERT INTO publication_theme (theme_name) VALUE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationTheme.getThemeName());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create publication theme in DB", e.getCause());
        }

    }

    @Override
    public PublicationTheme read(int id, Connection connection) {
        PublicationTheme publicationTheme = new PublicationTheme();
        String sql = "SELECT theme_name FROM publication_theme WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publicationTheme.setId(id);
                publicationTheme.setThemeName(resultSet.getString("theme_name"));
            }
        } catch (SQLException e) {
            logger.error("Can't read publication theme from DB", e.getCause());
        }


        return publicationTheme;
    }

    @Override
    public int readByName(Connection connection, String themeName) {
        int id = 0;
        String sql = "SELECT id FROM publication_theme WHERE theme_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, themeName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Can't read publication theme by name from DB", e.getCause());
        }
        return id;
    }

    @Override
    public void update(PublicationTheme publicationTheme, Connection connection) {
        String sql = "UPDATE publication_theme SET theme_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, publicationTheme.getThemeName());
            preparedStatement.setInt(2, publicationTheme.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update publication theme in DB", e.getCause());
        }
    }

    @Override
    public void delete(PublicationTheme publicationTheme, Connection connection) {
        String sql = "DELETE FROM publication_theme WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationTheme.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete publication theme in DB", e.getCause());
        }
    }

    @Override
    public List<PublicationTheme> getAll(Connection connection) {
        List<PublicationTheme> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_theme ORDER BY id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new PublicationTheme.Builder()
                        .setId(resultSet.getInt("id"))
                        .setThemeName(resultSet.getString("theme_name"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get publication themes from DB", e.getCause());
        }
        return list;
    }
}
