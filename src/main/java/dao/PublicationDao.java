package dao;

import beens.Publication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDao {

    public void create(Publication publication, Connection connection) throws SQLException;
    public Publication read(int id, Connection connection) throws SQLException;
    public int getLastPublicationId(Connection connection) throws SQLException;
    public void update(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, Connection connection) throws SQLException;
    public void delete(int publicationId, Connection connection) throws SQLException;
    public List<Publication> getAll(Connection connection) throws SQLException;
    public List<Publication> getByTypeThemeStatus(Connection connection, int typeId, int themeId, int statusId) throws SQLException;

    public List<Publication> getByType(Connection connection, int typeId) throws SQLException;
    public List<Publication> getByTheme(Connection connection, int themeId) throws SQLException;
    public List<Publication> getByStatus(Connection connection, int statusId) throws SQLException;

    public List<Publication> getByTypeByTheme(Connection connection, int typeId, int themeId) throws SQLException;
    public List<Publication> getByTypeByStatus(Connection connection, int typeId, int statusId) throws SQLException;
    public List<Publication> getByThemeByStatus(Connection connection, int themeId, int statusId) throws SQLException;

}
