package dao;

import beens.PublicationPeriodicityCost;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationPeriodicityCostDao {

    public void create(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) throws SQLException;
    public PublicationPeriodicityCost read(int id, Connection connection) throws SQLException;
//    public PublicationPeriodicityCost readByPubId(int pubId, Connection connection) throws SQLException;
    public void update(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) throws SQLException;
    public void delete(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) throws SQLException;
    public List<PublicationPeriodicityCost> getAll(Connection connection) throws SQLException;
    public List<PublicationPeriodicityCost> getAllByPubId(Connection connection, int pubId) throws SQLException;

}
