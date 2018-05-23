package dao.interfaces;

import beans.PublicationStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationStatusDao {

    void create(PublicationStatus publicationStatus, Connection connection);
    PublicationStatus read(int id, Connection connection);
    int readByName(Connection connection, String statusName);
    void update(PublicationStatus publicationStatus, Connection connection);
    void delete(PublicationStatus publicationStatus, Connection connection);
    List<PublicationStatus> getAll(Connection connection);

}
