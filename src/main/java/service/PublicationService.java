package service;

import connection.ConnectionPool;
import dao.*;
import beans.*;
import dao.interfaces.*;
import exceptions.DataBaseWorkException;
import exceptions.ErrorMassageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wrappers.EditPublicationWrapper;
import wrappers.FullPublicationInfoWrapper;
import wrappers.PublicThemeAndTypeWrapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PublicationService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

    public Publication getPublication(int pubId) {
        Connection connection = ConnectionPool.getConnection(true);
        Publication publication = null;
        try {
            publication = publicationDao.read(pubId, connection);

        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get publication", e.getCause());
            throw e;
        }
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
            for (PublicationPeriodicyCost cost : list) {
                cost.setPublicationId(publicationId);
                System.out.println("publicationId " + publicationId);
                publicationPeriodicityCostDao.create(cost, connection);
            }
            ConnectionPool.commitTransaction(connection);
        } catch (DataBaseWorkException e) {
            ConnectionPool.transactionRollback(connection);
            LOGGER.error("Can't create publication", e.getCause());
            throw e;
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
            ConnectionPool.commitTransaction(connection);
        } catch (DataBaseWorkException e) {
            ConnectionPool.transactionRollback(connection);
            LOGGER.error("Can't update publication", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public FullPublicationInfoWrapper aboutPublication(int publicationId) {
        FullPublicationInfoWrapper wrapper = null;
        Connection connection = ConnectionPool.getConnection(true);
        try {
            Publication publication = publicationDao.read(publicationId, connection);
            PublicationType publicationType = publicationTypeDao.read(publication.getPublicationTypeId(), connection);
            PublicationTheme publicationTheme = publicationThemeDao.read(publication.getPublicationThemeId(), connection);
            PublicationStatus publicationStatus = publicationStatusDao.read(publication.getPublicationStatusId(), connection);
            List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAllByPubId(connection, publicationId);
            wrapper = new FullPublicationInfoWrapper.Builder()
                    .setPublication(publication)
                    .setPublicationType(publicationType)
                    .setPublicationTheme(publicationTheme)
                    .setPublicationStatus(publicationStatus)
                    .setPublicationPeriodicyCostList(publicationPeriodicyCostList)
                    .build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get info about publication", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    public EditPublicationWrapper editPublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection(true);
        EditPublicationWrapper wrapper = null;
        try {
            Publication publication = publicationDao.read(publicationId, connection);
            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
            List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);
            List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAll(connection)
                    .stream()
                    .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == publicationId)
                    .collect(Collectors.toList());
            wrapper = new EditPublicationWrapper(publication, publicationTypeList, publicationThemeList, publicationStatusList, publicationPeriodicyCostList);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't edit publication", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    /*Working*/
    public List<Publication> getallPagination(int start, int total) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> list;
        try {
            list = publicationDao.getallPagination(connection, start, total);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get all publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return list;
    }

    public FullPublicationInfoWrapper getAllPublication() {
        Connection connection = ConnectionPool.getConnection(true);
        FullPublicationInfoWrapper wrapper = null;
        try {
            List<Publication> publicationList = publicationDao.getAll(connection);
            List<SubscriptionBill> subscriptionBillList = subscriptionBillDao.getAll(connection);
            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
            List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);
            wrapper = new FullPublicationInfoWrapper.Builder()
                    .setPublicationList(publicationList)
                    .setSubscriptionBillList(subscriptionBillList)
                    .setPublicationTypeList(publicationTypeList)
                    .setPublicationThemeList(publicationThemeList)
                    .setPublicationStatusList(publicationStatusList)
                    .build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get all publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    public PublicThemeAndTypeWrapper getPubThemesAndTypes() {
        Connection connection = ConnectionPool.getConnection(true);
        PublicThemeAndTypeWrapper wrapper = null;
        try {
            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
            wrapper = new PublicThemeAndTypeWrapper(publicationThemeList, publicationTypeList);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get publication themes and types", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    public List<Publication> getSelectedPublication(int pubTypeId, int pubThemeId, int pubStatusId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = null;
        try {
            publicationList = supportGetPubList(connection, pubTypeId, pubThemeId, pubStatusId);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get selected publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return publicationList;
    }

    private List<Publication> supportGetPubList(Connection connection, int pubTypeId, int pubThemeId, int pubStatusId) throws DataBaseWorkException {
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
        List<Publication> publicationList = null;
        try {
            publicationList = supportGetPubList(connection, typeId, themeId, 1);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get selected publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return publicationList;
    }

    public Map<Publication, List<PublicationPeriodicyCost>> getPublicationWithCosts(int typeId, int themeId, int statusId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<Publication, List<PublicationPeriodicyCost>> publicationListMap = null;
        List<Publication> publications;
        List<PublicationPeriodicyCost> publicationPeriodicyCosts;
        List<PublicationPeriodicyCost> forEachPub = new ArrayList<>();
        try {
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
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get publication with costs", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return publicationListMap;
    }
}
