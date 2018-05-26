package dao.interfaces;

import beans.Publication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDao {

    int create(Publication publication, Connection connection);
    Publication read(int id, Connection connection);
    int getLastPublicationId(Connection connection);
    void update(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, Connection connection);
    void delete(int publicationId, Connection connection);
    List<Publication> getAll(Connection connection);
    List<Publication> getByTypeThemeStatus(Connection connection, int typeId, int themeId, int statusId);

    List<Publication> getByType(Connection connection, int typeId);
    List<Publication> getByTheme(Connection connection, int themeId);
    List<Publication> getByStatus(Connection connection, int statusId);

    List<Publication> getByTypeByTheme(Connection connection, int typeId, int themeId);
    List<Publication> getByTypeByStatus(Connection connection, int typeId, int statusId);
    List<Publication> getByThemeByStatus(Connection connection, int themeId, int statusId);

}
