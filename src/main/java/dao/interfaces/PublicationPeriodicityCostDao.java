package dao.interfaces;

import beans.PublicationPeriodicyCost;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.util.List;

public interface PublicationPeriodicityCostDao {

    int create(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) throws DataBaseWorkException;
    PublicationPeriodicyCost read(int id, Connection connection) throws DataBaseWorkException;
//    public PublicationPeriodicyCost readByPubId(int pubId, Connection connection) throws SQLException;
    void update(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) throws DataBaseWorkException;
    void delete(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) throws DataBaseWorkException;
    List<PublicationPeriodicyCost> getAll(Connection connection) throws DataBaseWorkException;
    List<PublicationPeriodicyCost> getAllByPubId(Connection connection, int pubId) throws DataBaseWorkException;

}
