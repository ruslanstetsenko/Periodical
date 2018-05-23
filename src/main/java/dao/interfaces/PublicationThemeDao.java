package dao.interfaces;

import beans.PublicationTheme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationThemeDao {

    void create(PublicationTheme publicationTheme, Connection connection);
    PublicationTheme read(int id, Connection connection);
    int readByName(Connection connection, String themeName);
    void update(PublicationTheme publicationTheme, Connection connection);
    void delete(PublicationTheme publicationTheme, Connection connection);
    List<PublicationTheme> getAll(Connection connection);

}
