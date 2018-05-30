package dao.interfaces;

import beans.PublicationType;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationTypeDao {

    int create(PublicationType publicationType, Connection connection) throws DataBaseWorkException;
    PublicationType read(int id, Connection connection) throws DataBaseWorkException;
    int readByName(Connection connection, String typeName) throws DataBaseWorkException;
    void update(PublicationType publicationType, Connection connection) throws DataBaseWorkException;
    void delete(PublicationType publicationType, Connection connection) throws DataBaseWorkException;
    List<PublicationType> getAll(Connection connection) throws DataBaseWorkException;

}
