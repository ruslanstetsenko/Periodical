package dao;

import beens.PublicationTheme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationThemeDao {

    public void create(PublicationTheme publicationTheme, Connection connection) throws SQLException;
    public PublicationTheme read(int id, Connection connection) throws SQLException;
    public int readByName(Connection connection, String themeName) throws SQLException;
    public void update(PublicationTheme publicationTheme, Connection connection) throws SQLException;
    public void delete(PublicationTheme publicationTheme, Connection connection) throws SQLException;
    public List<PublicationTheme> getAll(Connection connection) throws SQLException;

}
