package dao;

import entity.Publication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDao {

    public void create(Publication publication, Connection connection) throws SQLException;
    public Publication read(int id, Connection connection) throws SQLException;
    public void update(Publication publication, Connection connection) throws SQLException;
    public void delete(Publication publication, Connection connection) throws SQLException;
    public List<Publication> getAll(Connection connection) throws SQLException;

}
