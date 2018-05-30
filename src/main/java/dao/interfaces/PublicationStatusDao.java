package dao.interfaces;

import beans.PublicationStatus;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationStatusDao {

    int create(PublicationStatus publicationStatus, Connection connection) throws DataBaseWorkException;
    PublicationStatus read(int id, Connection connection) throws DataBaseWorkException;
    int readByName(Connection connection, String statusName) throws DataBaseWorkException;
    void update(PublicationStatus publicationStatus, Connection connection) throws DataBaseWorkException;
    void delete(PublicationStatus publicationStatus, Connection connection) throws DataBaseWorkException;
    List<PublicationStatus> getAll(Connection connection) throws DataBaseWorkException;

}
