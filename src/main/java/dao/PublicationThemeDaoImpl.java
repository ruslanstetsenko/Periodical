package dao;

import beens.PublicationTheme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationThemeDaoImpl implements PublicationThemeDao {

    @Override
    public void create(PublicationTheme publicationTheme, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publication_theme (theme_name) VALUE ?");
        preparedStatement.setString(1, publicationTheme.getThemeName());
        preparedStatement.execute();
    }

    @Override
    public PublicationTheme read(int id, Connection connection) throws SQLException {
        PublicationTheme publicationTheme = new PublicationTheme();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT theme_name FROM publication_theme WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        publicationTheme.setId(id);
        publicationTheme.setThemeName(resultSet.getString("theme_name"));

        return publicationTheme;
    }

    @Override
    public int readByName(Connection connection, String themeName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM publication_theme WHERE theme_name=?");
        preparedStatement.setString(1, themeName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    @Override
    public void update(PublicationTheme publicationTheme, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publication_theme SET theme_name=? WHERE id=?");
        preparedStatement.setString(1, publicationTheme.getThemeName());
        preparedStatement.setInt(2, publicationTheme.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(PublicationTheme publicationTheme, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publication_theme WHERE id=?");
        preparedStatement.setInt(1, publicationTheme.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<PublicationTheme> getAll(Connection connection) throws SQLException {
        List<PublicationTheme> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM publication_theme ORDER BY id");

        while (resultSet.next()) {
            list.add(new PublicationTheme.Builder()
                    .setId(resultSet.getInt("id"))
                    .setThemeName(resultSet.getString("theme_name"))
                    .build());
        }
        return list;
    }
}
