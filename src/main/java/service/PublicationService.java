package service;

import connection.ConnectionPool;
import dao.*;
import beans.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PublicationService {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

    public Publication getPublication(int pubId) {
        Connection connection = ConnectionPool.getConnection(true);
        Publication publication = new Publication();
        try {
            publication = publicationDao.read(pubId, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return publication;
    }

    public void createPublication(String pubName, int issn, String website, Date setDate, int pubType, int pubStatus, int pubTheme, PublicationPeriodicityCost[] costs) {
        Connection connection = ConnectionPool.getConnection(false);
        Publication publication = new Publication.Builder()
                .setName(pubName).setIssnNumber(issn).setWebsite(website).setRegistrationDate(setDate).setPublicationTypeId(pubType).setPublicationStatusId(pubStatus).setPublicationThemeId(pubTheme).build();
        try {
            publicationDao.create(publication, connection);
            connection.commit();
            int publicationId = publicationDao.getLastPublicationId(connection);
            for (PublicationPeriodicityCost cost : costs) {
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
        Connection connection = ConnectionPool.getConnection(false);

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
        Connection connection = ConnectionPool.getConnection(true);
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
            publicationPeriodicityCostList = publicationPeriodicityCostDao.getAllByPubId(connection, publicationId);
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
        Connection connection = ConnectionPool.getConnection(true);
        Publication publication = new Publication();
        List<PublicationType> publicationTypeList = new ArrayList<>();
        List<PublicationTheme> publicationThemeList = new ArrayList<>();
        List<PublicationStatus> publicationStatusList = new ArrayList<>();
        List<PublicationPeriodicityCost> publicationPeriodicityCostList = new ArrayList<>();

        try {
            publication = publicationDao.read(publicationId, connection);
            publicationTypeList = publicationTypeDao.getAll(connection);
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
        Connection connection = ConnectionPool.getConnection(false);
        try {
            publicationDao.delete(publication.getId(), connection);
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

    public List[] getAllPublication() {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        List<PublicationType> publicationTypeList = new ArrayList<>();
        List<PublicationTheme> publicationThemeList = new ArrayList<>();
        List<PublicationStatus> publicationStatusList = new ArrayList<>();

        try {
            publicationList = publicationDao.getAll(connection);
            subscriptionBillList = subscriptionBillDao.getAll(connection);
            publicationTypeList = publicationTypeDao.getAll(connection);
            publicationThemeList = publicationThemeDao.getAll(connection);
            publicationStatusList = publicationStatusDao.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new List[]{publicationList, subscriptionBillList, publicationTypeList, publicationThemeList, publicationStatusList};
    }

    public List[] getPubThemesAndTypes() {
        Connection connection = ConnectionPool.getConnection(true);
        List<PublicationType> publicationTypeList = new ArrayList<>();
        List<PublicationTheme> publicationThemeList = new ArrayList<>();

        try {
            publicationTypeList = publicationTypeDao.getAll(connection);
            publicationThemeList = publicationThemeDao.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new List[]{publicationTypeList, publicationThemeList};
    }

    public List[] getSelectedPublication(int pubTypeId, int pubThemeId, int pubStatusId, int billStatusId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        List<PublicationType> publicationTypeList = new ArrayList<>();
        List<PublicationTheme> publicationThemeList = new ArrayList<>();
        List<PublicationStatus> publicationStatusList = new ArrayList<>();

        try {
            if (billStatusId == 0) {
                subscriptionBillList = subscriptionBillDao.getAll(connection);
                publicationList = supportGetPubList(connection, pubTypeId, pubThemeId, pubStatusId);
//                System.out.println(publicationList);

            } else {
                subscriptionBillList = subscriptionBillDao.getByStatus(connection, billStatusId);
                publicationList = supportGetPubList(connection, pubTypeId, pubThemeId, pubStatusId);
//                System.out.println(publicationList + "\n");

            }
            publicationTypeList = publicationTypeDao.getAll(connection);
            publicationThemeList = publicationThemeDao.getAll(connection);
            publicationStatusList = publicationStatusDao.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new List[]{publicationList, subscriptionBillList, publicationTypeList, publicationThemeList, publicationStatusList};
    }

    private List<Publication> supportGetPubList(Connection connection, int pubTypeId, int pubThemeId, int pubStatusId) throws SQLException {
        List<Publication> publicationList = new ArrayList<>();
        if (pubTypeId == 0 && pubThemeId == 0 && pubStatusId == 0) {
            publicationList = publicationDao.getAll(connection);
        } else if (pubTypeId != 0 && pubThemeId != 0 && pubStatusId != 0) {
            publicationList = publicationDao.getByTypeThemeStatus(connection, pubTypeId, pubThemeId, pubStatusId);
        } else if (pubTypeId != 0 && pubThemeId != 0) {
            publicationList = publicationDao.getByTypeByTheme(connection, pubTypeId, pubThemeId);
        } else if (pubTypeId != 0 && pubStatusId != 0) {
            publicationList = publicationDao.getByTypeByStatus(connection, pubTypeId, pubStatusId);
        } else if (pubThemeId != 0 && pubStatusId != 0) {
            publicationList = publicationDao.getByThemeByStatus(connection, pubThemeId, pubStatusId);
        } else if (pubTypeId != 0) {
            publicationList = publicationDao.getByType(connection, pubTypeId);
        } else if (pubThemeId != 0) {
            publicationList = publicationDao.getByTheme(connection, pubThemeId);
        } else {
            publicationList = publicationDao.getByStatus(connection, pubStatusId);
        }
        return publicationList;
    }

    public List<Publication> selectPublicationsByTypeByTheme(int typeId, int themeId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        try {
            publicationList = supportGetPubList(connection, themeId, themeId, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicationList;
    }

    public List<Publication> selectPublicationsByStatus(PublicationStatus status, int currentPubTypeId, int currentPubThemeId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationStatusId() == status.getId())
                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
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
        return publicationList;
    }

    public List<Publication> selectPublicationsByTheme(PublicationTheme theme, int currentPubStatusId, int currentPubTypeId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationThemeId() == theme.getId())
                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
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
        return publicationList;
    }


    public List<Publication> selectPublicationsByType(PublicationType type, int currentPubStatusId, int currentPubThemeId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = new ArrayList<>();
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationTypeId() == type.getId())
                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
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
        return publicationList;
    }

    public void addNewPublication(String name, int issnNumber, Date registrationDate, String website, Integer publicationTypeId, Integer publicationStatusId, Integer publicationThemeId, int currentPubStatusId, int currentPubTypeId, int currentPubThemeId) {
        Connection connection = ConnectionPool.getConnection(false);
        List<Publication> publicationList = new ArrayList<>();
        Publication publicationNew = new Publication.Builder()
                .setName(name)
                .setIssnNumber(issnNumber)
                .setRegistrationDate(registrationDate)
                .setWebsite(website)
                .setPublicationTypeId(publicationTypeId)
                .setPublicationStatusId(publicationStatusId)
                .build();
        try {
            publicationDao.create(publicationNew, connection);
            connection.commit();
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
                    .collect(Collectors.toList());
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


    public void deletePublication(int publicationId, int currentPubStatusId, int currentPubTypeId, int currentPubThemeId) {
        Connection connection = ConnectionPool.getConnection(false);
        List<Publication> publicationList = new ArrayList<>();
        try {
            publicationDao.delete(publicationId, connection);
            connection.commit();
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
                    .collect(Collectors.toList());
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

    public Map<Publication, List<PublicationPeriodicityCost>> getPublicationWithCosts(int typeId, int themeId, int statusId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<Publication, List<PublicationPeriodicityCost>> publicationListMap = new LinkedHashMap<>();
        List<Publication> publications = new ArrayList<>();
        List<PublicationPeriodicityCost> publicationPeriodicityCosts = new ArrayList<>();
        List<PublicationPeriodicityCost> forEachPub = new ArrayList<>();

        try {
            publications = supportGetPubList(connection, typeId, themeId, statusId);
            publicationPeriodicityCosts = publicationPeriodicityCostDao.getAll(connection)
                    .stream()
                    .sorted(Comparator.comparing(PublicationPeriodicityCost::getPublicationId))
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
        for (Publication publication : publications) {
            for (PublicationPeriodicityCost cost : publicationPeriodicityCosts) {
                if (cost.getPublicationId() == publication.getId()) {
                    forEachPub.add(cost);
                }
            }

            publicationListMap.put(publication, forEachPub);
            forEachPub = new ArrayList<>();
        }
        return publicationListMap;
    }
}
