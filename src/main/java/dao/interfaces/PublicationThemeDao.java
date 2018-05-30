package dao.interfaces;

import beans.PublicationTheme;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationThemeDao {

    int create(PublicationTheme publicationTheme, Connection connection) throws DataBaseWorkException;
    PublicationTheme read(int id, Connection connection) throws DataBaseWorkException;
    int readByName(Connection connection, String themeName) throws DataBaseWorkException;
    void update(PublicationTheme publicationTheme, Connection connection) throws DataBaseWorkException;
    void delete(PublicationTheme publicationTheme, Connection connection) throws DataBaseWorkException;
    List<PublicationTheme> getAll(Connection connection) throws DataBaseWorkException;

}
