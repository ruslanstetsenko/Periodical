package service;

import connection.ConnectionPool;
import dao.*;
import beens.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PublicationService {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();

    List<PublicationPeriodicityCost> publicationPeriodicityCostList;

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

    public void updatePublication(Publication publication) {
        Connection connection = ConnectionPool.getConnection();
        try {
            publicationDao.update(publication, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAboutPublication(Publication publication) {
        Connection connection = ConnectionPool.getConnection();
        try {
            PublicationType publicationType = publicationTypeDao.read(publication.getPublicationTypeId(), connection);
            PublicationTheme publicationTheme = publicationThemeDao.read(publication.getPublicationThemeId(), connection);
            publicationPeriodicityCostList = publicationPeriodicityCostDao.getAll(connection)
                    .stream()
                    .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == publication.getId())
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
