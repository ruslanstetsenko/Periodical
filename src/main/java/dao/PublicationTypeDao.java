package dao;

import entity.PublicationType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationTypeDao {

    public void create(PublicationType publicationType, Connection connection) throws SQLException;
    public PublicationType read(int id, Connection connection) throws SQLException;
    public void update(PublicationType publicationType, Connection connection) throws SQLException;
    public void delete(PublicationType publicationType, Connection connection) throws SQLException;
    public List<PublicationType> getAll(Connection connection) throws SQLException;

}