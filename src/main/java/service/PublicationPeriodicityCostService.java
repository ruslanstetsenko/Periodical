package service;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.PublicationPeriodicityCostDao;
import beans.PublicationPeriodicityCost;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PublicationPeriodicityCostService {

    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private List<PublicationPeriodicityCost> publicationPeriodicityCostList;

    public PublicationPeriodicityCost getPubPeriodicyCost(int id) {
        Connection connection = ConnectionPool.getConnection(true);
        PublicationPeriodicityCost bean = new PublicationPeriodicityCost();
        bean = publicationPeriodicityCostDao.read(id, connection);

        ConnectionPool.closeConnection(connection);
        return bean;
    }

    public double getCostValue(int costId) {
        Connection connection = ConnectionPool.getConnection(true);
        double value = 0.0;
        value = publicationPeriodicityCostDao.read(costId, connection).getCost();
        ConnectionPool.closeConnection(connection);
        return value;
    }

    public void update(PublicationPeriodicityCost cost) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            publicationPeriodicityCostDao.update(cost, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(PublicationPeriodicityCost cost) {
        Connection connection = ConnectionPool.getConnection(false);
        try {
            publicationPeriodicityCostDao.delete(cost, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void addToList(int timePerYears, double cost) {
        publicationPeriodicityCostList.add(new PublicationPeriodicityCost.Builder().setTimesPerYear(timePerYears).setCost(cost).build());
    }

    public List<PublicationPeriodicityCost> getPublicationPeriodicityCostList() {
        return publicationPeriodicityCostList;
    }
}
