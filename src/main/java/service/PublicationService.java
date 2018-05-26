package service;

import connection.ConnectionPool;
import dao.*;
import beans.*;
import dao.interfaces.*;
import wrappers.EditPublicationWrapper;
import wrappers.FullPublicationInfoWrapper;
import wrappers.PublicThemeAndTypeWrapper;

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
        Publication publication = publicationDao.read(pubId, connection);
        ConnectionPool.closeConnection(connection);
        return publication;
    }

    public void createPublication(String pubName, String issn, String website, String setDate, String pubType, String pubStatus, String pubTheme, String cost1M, String cost3M, String cost6M, String cost12M) {
        Connection connection = ConnectionPool.getConnection(false);
        List<PublicationPeriodicyCost> list = new ArrayList<>();

        PublicationPeriodicyCost pubCost1M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(1)
                .setCost(Double.valueOf(cost1M))
                .build();
        PublicationPeriodicyCost pubCost3M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(3)
                .setCost(Double.valueOf(cost3M))
                .build();
        PublicationPeriodicyCost pubCost6M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(6)
                .setCost(Double.valueOf(cost6M))
                .build();
        PublicationPeriodicyCost pubCost12M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(12)
                .setCost(Double.valueOf(cost12M))
                .build();

        list.add(pubCost1M);
        list.add(pubCost3M);
        list.add(pubCost6M);
        list.add(pubCost12M);

        int issn1 = Integer.valueOf(issn);
        Date setDate1 = Date.valueOf(setDate);
        int pubType1 = Integer.valueOf(pubType);
        int pubStatus1 = Integer.valueOf(pubStatus);
        int pubTheme1 = Integer.valueOf(pubTheme);

        Publication publication = new Publication.Builder()
                .setName(pubName)
                .setIssnNumber(issn1)
                .setWebsite(website)
                .setRegistrationDate(setDate1)
                .setPublicationTypeId(pubType1)
                .setPublicationStatusId(pubStatus1)
                .setPublicationThemeId(pubTheme1)
                .build();
        try {
            int publicationId = publicationDao.create(publication, connection);

//            int publicationId = publicationDao.getLastPublicationId(connection);
            for (PublicationPeriodicyCost cost : list) {
                cost.setPublicationId(publicationId);
                System.out.println("publicationId " + publicationId);
                publicationPeriodicityCostDao.create(cost, connection);
            }
            connection.commit();
        } catch (SQLException e) {
            ConnectionPool.transactionRollback(connection);
        } finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public void updatePublication(int publicationId, String pubName, String issn, String website, String setDate, String publicationType, String publicationTheme, String publicationStatus, Map<Integer, String> costs, List<PublicationPeriodicyCost> costBeens) {
        Connection connection = ConnectionPool.getConnection(false);

        int issn1 = Integer.valueOf(issn);
        Date setDate1 = Date.valueOf(setDate);
        int publicationType1 = Integer.valueOf(publicationType);
        int publicationTheme1 = Integer.valueOf(publicationTheme);
        int publicationStatus1 = Integer.valueOf(publicationStatus);
        try {
            publicationDao.update(publicationId, pubName, issn1, website, setDate1, publicationType1, publicationTheme1, publicationStatus1, connection);
            for (Map.Entry<Integer, String> entry : costs.entrySet()) {
                for (int i = 0; i < costs.size(); i++) {
                    PublicationPeriodicyCost publicationPeriodicyCost = costBeens.get(i);
                    if (publicationPeriodicyCost.getTimesPerYear() == entry.getKey()) {
                        if (entry.getValue() != null) {
                            publicationPeriodicyCost.setCost(Double.valueOf(entry.getValue()));
                        } else {
                            publicationPeriodicyCost.setCost(0.0);
                        }
                        publicationPeriodicityCostDao.update(publicationPeriodicyCost, connection);
                        break;
                    }
                }
            }

//            for (int i = 0; i < costs.size(); i++) {
//                PublicationPeriodicyCost publicationPeriodicyCost = costBeens.get(i);
//                if (costs.)
//                publicationPeriodicyCost.setCost(Double.valueOf(costs[i]));
//                publicationPeriodicityCostDao.update(publicationPeriodicyCost, connection);
//            }
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

    public FullPublicationInfoWrapper aboutPublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection(true);
        Publication publication = publicationDao.read(publicationId, connection);
        PublicationType publicationType = publicationTypeDao.read(publication.getPublicationTypeId(), connection);
        PublicationTheme publicationTheme = publicationThemeDao.read(publication.getPublicationThemeId(), connection);
        PublicationStatus publicationStatus = publicationStatusDao.read(publication.getPublicationStatusId(), connection);
        List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAllByPubId(connection, publicationId);

        ConnectionPool.closeConnection(connection);
        return new FullPublicationInfoWrapper.Builder()
                .setPublication(publication)
                .setPublicationType(publicationType)
                .setPublicationTheme(publicationTheme)
                .setPublicationStatus(publicationStatus)
                .setPublicationPeriodicyCostList(publicationPeriodicyCostList)
                .build();
    }

    public EditPublicationWrapper editPublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection(true);

        Publication publication = publicationDao.read(publicationId, connection);
        List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
        List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
        List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);
        List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAll(connection)
                .stream()
                .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == publicationId)
                .collect(Collectors.toList());

        ConnectionPool.closeConnection(connection);
        return new EditPublicationWrapper(publication, publicationTypeList, publicationThemeList, publicationStatusList, publicationPeriodicyCostList);
    }

//    public void deletePublication(Publication publication) {
//        Connection connection = ConnectionPool.getConnection(false);
//        try {
//            publicationDao.delete(publication.getId(), connection);
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public FullPublicationInfoWrapper getAllPublication() {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = publicationDao.getAll(connection);
        List<SubscriptionBill> subscriptionBillList = subscriptionBillDao.getAll(connection);
        List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
        List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
        List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);

        ConnectionPool.closeConnection(connection);
        return new FullPublicationInfoWrapper.Builder()
                .setPublicationList(publicationList)
                .setSubscriptionBillList(subscriptionBillList)
                .setPublicationTypeList(publicationTypeList)
                .setPublicationThemeList(publicationThemeList)
                .setPublicationStatusList(publicationStatusList)
                .build();
    }

    public PublicThemeAndTypeWrapper getPubThemesAndTypes() {
        Connection connection = ConnectionPool.getConnection(true);
        List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
        List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);

        ConnectionPool.closeConnection(connection);
        return new PublicThemeAndTypeWrapper(publicationThemeList, publicationTypeList);
    }

    public List<Publication> getSelectedPublication(int pubTypeId, int pubThemeId, int pubStatusId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList;
//        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
//        List<PublicationType> publicationTypeList = new ArrayList<>();
//        List<PublicationTheme> publicationThemeList = new ArrayList<>();
//        List<PublicationStatus> publicationStatusList = new ArrayList<>();

//        publicationTypeList = publicationTypeDao.getAll(connection);
//        publicationThemeList = publicationThemeDao.getAll(connection);
//        publicationStatusList = publicationStatusDao.getAll(connection);
        publicationList = supportGetPubList(connection, pubTypeId, pubThemeId, pubStatusId);
        ConnectionPool.closeConnection(connection);
        return publicationList;
    }

    private List<Publication> supportGetPubList(Connection connection, int pubTypeId, int pubThemeId, int pubStatusId) {
        List<Publication> publicationList;
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
        List<Publication> publicationList = supportGetPubList(connection, typeId, themeId, 1);

        ConnectionPool.closeConnection(connection);
        return publicationList;
    }

//    public List<Publication> selectPublicationsByStatus(PublicationStatus status, int currentPubTypeId, int currentPubThemeId) {
//        Connection connection = ConnectionPool.getConnection(true);
//        List<Publication> publicationList = publicationDao.getAll(connection)
//                .stream()
//                .filter(publication -> publication.getPublicationStatusId() == status.getId())
//                .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
//                .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
//                .collect(Collectors.toList());
//
//        ConnectionPool.closeConnection(connection);
//        return publicationList;
//    }

//    public List<Publication> selectPublicationsByTheme(PublicationTheme theme, int currentPubStatusId, int currentPubTypeId) {
//        Connection connection = ConnectionPool.getConnection(true);
//        List<Publication> publicationList = publicationDao.getAll(connection)
//                .stream()
//                .filter(publication -> publication.getPublicationThemeId() == theme.getId())
//                .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
//                .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
//                .collect(Collectors.toList());
//
//        ConnectionPool.closeConnection(connection);
//        return publicationList;
//    }


//    public List<Publication> selectPublicationsByType(PublicationType type, int currentPubStatusId, int currentPubThemeId) {
//        Connection connection = ConnectionPool.getConnection(true);
//
//        List<Publication> publicationList = publicationDao.getAll(connection)
//                .stream()
//                .filter(publication -> publication.getPublicationTypeId() == type.getId())
//                .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
//                .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
//                .collect(Collectors.toList());
//
//        ConnectionPool.closeConnection(connection);
//        return publicationList;
//    }

//    public void addNewPublication(String name, int issnNumber, Date registrationDate, String website, Integer publicationTypeId, Integer publicationStatusId, Integer publicationThemeId, int currentPubStatusId, int currentPubTypeId, int currentPubThemeId) {
//        Connection connection = ConnectionPool.getConnection(false);
//        Publication publicationNew = new Publication.Builder()
//                .setName(name)
//                .setIssnNumber(issnNumber)
//                .setRegistrationDate(registrationDate)
//                .setWebsite(website)
//                .setPublicationTypeId(publicationTypeId)
//                .setPublicationStatusId(publicationStatusId)
//                .build();
//        try {
//            publicationDao.create(publicationNew, connection);
//            connection.commit();
//            List<Publication> publicationList = publicationDao.getAll(connection)
//                    .stream()
//                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
//                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
//                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
//                    .collect(Collectors.toList());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public void deletePublication(int publicationId, int currentPubStatusId, int currentPubTypeId, int currentPubThemeId) {
//        Connection connection = ConnectionPool.getConnection(false);
//        List<Publication> publicationList = new ArrayList<>();
//        try {
//            publicationDao.delete(publicationId, connection);
//            connection.commit();
//            publicationList = publicationDao.getAll(connection)
//                    .stream()
//                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
//                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
//                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
//                    .collect(Collectors.toList());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public Map<Publication, List<PublicationPeriodicyCost>> getPublicationWithCosts(int typeId, int themeId, int statusId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<Publication, List<PublicationPeriodicyCost>> publicationListMap = new LinkedHashMap<>();
        List<Publication> publications = new ArrayList<>();
        List<PublicationPeriodicyCost> publicationPeriodicyCosts = new ArrayList<>();
        List<PublicationPeriodicyCost> forEachPub = new ArrayList<>();


        publications = supportGetPubList(connection, typeId, themeId, statusId);
        publicationPeriodicyCosts = publicationPeriodicityCostDao.getAll(connection)
                .stream()
                .sorted(Comparator.comparing(PublicationPeriodicyCost::getPublicationId))
                .collect(Collectors.toList());

        for (Publication publication : publications) {
            for (PublicationPeriodicyCost cost : publicationPeriodicyCosts) {
                if (cost.getPublicationId() == publication.getId()) {
                    forEachPub.add(cost);
                }
            }

            publicationListMap.put(publication, forEachPub);
            forEachPub = new ArrayList<>();
        }

        ConnectionPool.closeConnection(connection);
        return publicationListMap;
    }
}
