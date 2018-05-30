package service;

import beans.PublicationPeriodicyCost;
import connection.ConnectionPool;
import dao.DaoFactory;
import dao.interfaces.PublicationPeriodicityCostDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class PublicationPeriodicityCostService {
    private static final Logger LOGGER = LogManager.getLogger(PublicationPeriodicityCostService.class);

    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();

    public PublicationPeriodicyCost getPubPeriodicyCost(int id) {
        PublicationPeriodicyCost bean;
        Connection connection = ConnectionPool.getConnection(true);
        try {
            bean = publicationPeriodicityCostDao.read(id, connection);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get periodicy cost", e.getCause());
            throw e;
        }
        return bean;
    }
}
