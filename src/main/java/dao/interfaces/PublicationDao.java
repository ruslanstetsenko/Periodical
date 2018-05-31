package dao.interfaces;

import beans.Publication;
import connection.ConnectionPool;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDao {

    int create(Publication publication, Connection connection) throws DataBaseWorkException;
    Publication read(int id, Connection connection) throws DataBaseWorkException;
    int getLastPublicationId(Connection connection) throws DataBaseWorkException;
    void update(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, Connection connection) throws DataBaseWorkException;
    void delete(int publicationId, Connection connection) throws DataBaseWorkException;
    List<Publication> getAll(Connection connection) throws DataBaseWorkException;
    List<Publication> getByTypeThemeStatus(Connection connection, int typeId, int themeId, int statusId) throws DataBaseWorkException;

    List<Publication> getByType(Connection connection, int typeId) throws DataBaseWorkException;
    List<Publication> getByTheme(Connection connection, int themeId) throws DataBaseWorkException;
    List<Publication> getByStatus(Connection connection, int statusId) throws DataBaseWorkException;

    List<Publication> getByTypeByTheme(Connection connection, int typeId, int themeId) throws DataBaseWorkException;
    List<Publication> getByTypeByStatus(Connection connection, int typeId, int statusId) throws DataBaseWorkException;
    List<Publication> getByThemeByStatus(Connection connection, int themeId, int statusId) throws DataBaseWorkException;
}
