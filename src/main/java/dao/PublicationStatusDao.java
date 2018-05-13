package dao;

import beens.PublicationStatus;
import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationStatusDao {

    public void create(PublicationStatus publicationStatus, Connection connection) throws SQLException;
    public PublicationStatus read(int id, Connection connection) throws SQLException;
    public int readByName(Connection connection, String statusName) throws SQLException;
    public void update(PublicationStatus publicationStatus, Connection connection) throws SQLException;
    public void delete(PublicationStatus publicationStatus, Connection connection) throws SQLException;
    public List<PublicationStatus> getAll(Connection connection) throws SQLException;

}
