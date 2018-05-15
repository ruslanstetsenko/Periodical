package service;

import beens.PublicationPeriodicityCost;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.PublicationPeriodicityCostDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodicyCostService {
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();

    public List<PublicationPeriodicityCost> getPeriodicyCosts() {
        Connection connection = ConnectionPool.getConnection(true);
        List<PublicationPeriodicityCost> list = new ArrayList<>();
        try {
            list = publicationPeriodicityCostDao.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
