package service;

import connection.ConnectionPool;
import dao.*;
import beens.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicationService {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();

    public void createPublication(Publication publication, PublicationType publicationType, PublicationTheme publicationTheme, List<PublicationPeriodicityCost> periodicityCosts) {
        Connection connection = ConnectionPool.getConnection();
        publication.setPublicationTypeId(publicationType.getId());
        publication.setPublicationStatusId(1);
        publication.setPublicationThemeId(publicationTheme.getId());
        try {
            publicationDao.create(publication, connection);
            int publicationId = publicationDao.getLastPublicationId(connection);
            for (PublicationPeriodicityCost cost : periodicityCosts) {
                cost.setPublicationId(publicationId);
                publicationPeriodicityCostDao.create(cost, connection);
            }
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

    public void updatePublication(int publicationId, String pubName, int issn, String website, Date setDate, int publicationType, int publicationTheme, int publicationStatus, String[] costs, List<PublicationPeriodicityCost> costBeens) {
        Connection connection = ConnectionPool.getConnection();

        try {
            publicationDao.update(publicationId, pubName, issn, website, setDate, publicationType, publicationTheme, publicationStatus, connection);
            for (int i = 0; i < costs.length; i++) {
                PublicationPeriodicityCost publicationPeriodicityCost = costBeens.get(i);
                publicationPeriodicityCost.setCost(Double.valueOf(costs[i]));
                publicationPeriodicityCostDao.update(publicationPeriodicityCost, connection);
            }
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

    public Object[] aboutPublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection();
        Publication publication = new Publication();
        PublicationType publicationType = new PublicationType();
        PublicationTheme publicationTheme = new PublicationTheme();
        PublicationStatus publicationStatus = new PublicationStatus();
        List<PublicationPeriodicityCost> publicationPeriodicityCostList = new ArrayList<>();
        
        try {
            publication = publicationDao.read(publicationId, connection);
            publicationType = publicationTypeDao.read(publication.getPublicationTypeId(), connection);
            publicationTheme = publicationThemeDao.read(publication.getPublicationThemeId(), connection);
            publicationStatus = publicationStatusDao.read(publication.getPublicationStatusId(), connection);
            publicationPeriodicityCostList = publicationPeriodicityCostDao.getAll(connection)
                    .stream()
                    .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == publicationId)
                    .collect(Collectors.toList());
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Object[]{publication, publicationType, publicationTheme, publicationStatus, publicationPeriodicityCostList};
    }

    public Object[] editPublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection();
        Publication publication = new Publication();
        List<PublicationType> publicationTypeList = new ArrayList<>();
        List<PublicationTheme> publicationThemeList = new ArrayList<>();
        List<PublicationStatus> publicationStatusList = new ArrayList<>();
        List<PublicationPeriodicityCost> publicationPeriodicityCostList = new ArrayList<>();

        try {
            publication = publicationDao.read(publicationId, connection);
            publicationTypeList= publicationTypeDao.getAll(connection);
            publicationThemeList = publicationThemeDao.getAll(connection);
            publicationStatusList = publicationStatusDao.getAll(connection);
            publicationPeriodicityCostList = publicationPeriodicityCostDao.getAll(connection)
                    .stream()
                    .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == publicationId)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Object[]{publication, publicationTypeList, publicationThemeList, publicationStatusList, publicationPeriodicityCostList};
    }

    public void deletePublication(Publication publication) {
        Connection connection = ConnectionPool.getConnection();
        try {
            publicationDao.delete(publication.getId(), connection);
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
}
