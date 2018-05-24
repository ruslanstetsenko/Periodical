package dao.interfaces;

import beans.PublicationPeriodicyCost;

import java.sql.Connection;
import java.util.List;

public interface PublicationPeriodicityCostDao {

    void create(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection);
    PublicationPeriodicyCost read(int id, Connection connection);
//    public PublicationPeriodicyCost readByPubId(int pubId, Connection connection) throws SQLException;
    void update(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection);
    void delete(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection);
    List<PublicationPeriodicyCost> getAll(Connection connection);
    List<PublicationPeriodicyCost> getAllByPubId(Connection connection, int pubId);

}
