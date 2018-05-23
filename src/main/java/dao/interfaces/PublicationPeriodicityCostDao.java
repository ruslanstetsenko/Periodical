package dao.interfaces;

import beans.PublicationPeriodicityCost;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationPeriodicityCostDao {

    void create(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection);
    PublicationPeriodicityCost read(int id, Connection connection);
//    public PublicationPeriodicityCost readByPubId(int pubId, Connection connection) throws SQLException;
    void update(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection);
    void delete(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection);
    List<PublicationPeriodicityCost> getAll(Connection connection);
    List<PublicationPeriodicityCost> getAllByPubId(Connection connection, int pubId);

}
