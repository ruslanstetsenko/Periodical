package service;

import beans.PublicationPeriodicyCost;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.PublicationPeriodicityCostDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

/**
 * Publication cost service. Get publication cost from database
 * @author Stetsenko Ruslan
 */
public class PublicationPeriodicityCostService {
    private static final Logger LOGGER = LogManager.getLogger(PublicationPeriodicityCostService.class);

    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();

    /**
     * Get publication cost from database
     * @param id publication cost id number in database
     * @return publication cost from the database if it exists or null
     * @exception DataBaseWorkException errors in DAO layer
     */
    public PublicationPeriodicyCost getPubPeriodicyCost(int id) {
        PublicationPeriodicyCost bean;
        Connection connection = ConnectionPool.getConnection(true);
        try {
            bean = publicationPeriodicityCostDao.read(id, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get periodicy cost", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return bean;
    }
}
