package dao.interfaces;

import beans.PublicationType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationTypeDao {

    void create(PublicationType publicationType, Connection connection);
    PublicationType read(int id, Connection connection);
    int readByName(Connection connection, String typeName);
    void update(PublicationType publicationType, Connection connection);
    void delete(PublicationType publicationType, Connection connection);
    List<PublicationType> getAll(Connection connection);

}
