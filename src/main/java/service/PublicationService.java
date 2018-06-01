package service;

import connection.ConnectionPool;
import dao.*;
import beans.*;
import dao.interfaces.*;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wrappers.FullPublicationInfoWrapper;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

/**
 * Publication service. Working with publications
 * @author Stetsenko Ruslan
 */
public class PublicationService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

    /**
     * Get publication from database
     * @param id id publication's number in database
     * @return publication from the database if it exists or null
     * @throws DataBaseWorkException errors in DAO layer
     */
    public Publication getPublication(int id) {
        Connection connection = ConnectionPool.getConnection(true);
        Publication publication = null;
        try {
            publication = publicationDao.read(id, connection);

        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get publication", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return publication;
    }

    /**
     * Create publication in database
     * @param pubName   publication's title
     * @param issn      publication's ISSN number
     * @param website   publication's website
     * @param setDate   publication's registration date
     * @param pubType   id number publication's type
     * @param pubStatus id number publication's status
     * @param pubTheme  id number publication's theme
     * @param cost1M    value of publication subscription cost for 1 month
     * @param cost3M    value of publication subscription cost for 3 month
     * @param cost6M    value of publication subscription cost for 6 month
     * @param cost12M   value of publication subscription cost for 12 month
     * @throws DataBaseWorkException errors in DAO layer
     */
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

    /**
     * Update publication
     * @param id        publication's id number
     * @param pubName   publication's title
     * @param issn      publication's ISSN number
     * @param website   publication's website
     * @param setDate   publication's registration date
     * @param pubType   id number publication's type
     * @param pubStatus id number publication's status
     * @param pubTheme  id number publication's themes
     * @param costs     map with pairs of values period - subscription price
     * @param costBeens list of cost options before the change
     * @throws DataBaseWorkException errors in DAO layer
     */
    public void updatePublication(int id, String pubName, String issn, String website, String setDate, String pubType, String pubTheme, String pubStatus, Map<Integer, String> costs, List<PublicationPeriodicyCost> costBeens) {
        Connection connection = ConnectionPool.getConnection(false);

        int issn1 = Integer.valueOf(issn);
        Date setDate1 = Date.valueOf(setDate);
        int publicationType1 = Integer.valueOf(pubType);
        int publicationTheme1 = Integer.valueOf(pubTheme);
        int publicationStatus1 = Integer.valueOf(pubStatus);
        try {
            publicationDao.update(id, pubName, issn1, website, setDate1, publicationType1, publicationTheme1, publicationStatus1, connection);
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

    /**
     * Get publication and full information about it
     * @param id publication's id
     * @return wrapper object with publication's information or null
     * @throws DataBaseWorkException errors in DAO layer
     * @see FullPublicationInfoWrapper
     */
    public FullPublicationInfoWrapper aboutPublication(int id) {
        FullPublicationInfoWrapper wrapper = null;
        Connection connection = ConnectionPool.getConnection(true);
        try {
            Publication publication = publicationDao.read(id, connection);
            PublicationType publicationType = publicationTypeDao.read(publication.getPublicationTypeId(), connection);
            PublicationTheme publicationTheme = publicationThemeDao.read(publication.getPublicationThemeId(), connection);
            PublicationStatus publicationStatus = publicationStatusDao.read(publication.getPublicationStatusId(), connection);
            List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAllByPubId(connection, id);
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

    /**
     * Get publication and information about cost option for update it
     * @param id publication's id
     * @return wrapper object with publication's information
     * @throws DataBaseWorkException errors in DAO layer
     * @see FullPublicationInfoWrapper
     */
    public FullPublicationInfoWrapper aboutPublicationForUpdate(int id) {
        Connection connection = ConnectionPool.getConnection(true);
        FullPublicationInfoWrapper wrapper = null;
        try {
            Publication publication = publicationDao.read(id, connection);
//            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
//            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
//            List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);
            List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAllByPubId(connection, id);
//            List<PublicationPeriodicyCost> publicationPeriodicyCostList = publicationPeriodicityCostDao.getAll(connection)
//                    .stream()
//                    .filter(publicationPeriodicityCost -> publicationPeriodicityCost.getPublicationId() == id)
//                    .collect(Collectors.toList());
            wrapper = new FullPublicationInfoWrapper.Builder()
                    .setPublication(publication)
//                    .setPublicationTypeList(publicationTypeList)
//                    .setPublicationThemeList(publicationThemeList)
//                    .setPublicationStatusList(publicationStatusList)
                    .setPublicationPeriodicyCostList(publicationPeriodicyCostList)
                    .build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't edit publication", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    /**
     * Get all publications and full information about theme
     * @return wrapper object with publication's information
     * @throws DataBaseWorkException errors in DAO layer
     * @see FullPublicationInfoWrapper
     */
    public FullPublicationInfoWrapper getAllPublications() {
        Connection connection = ConnectionPool.getConnection(true);
        FullPublicationInfoWrapper wrapper = null;
        try {
            List<Publication> publicationList = publicationDao.getAll(connection);
            List<SubscriptionBill> subscriptionBillList = subscriptionBillDao.getAll(connection);
            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
            List<PublicationStatus> publicationStatusList = publicationStatusDao.getAll(connection);
            Map<SubscriptionBill, User> subscriptionBillUserMap = subscriptionBillDao.getBillWithUsersByStatus(connection, 0);
            wrapper = new FullPublicationInfoWrapper.Builder()
                    .setPublicationList(publicationList)
                    .setSubscriptionBillList(subscriptionBillList)
                    .setPublicationTypeList(publicationTypeList)
                    .setPublicationThemeList(publicationThemeList)
                    .setPublicationStatusList(publicationStatusList)
                    .setSubscriptionBillUserMap(subscriptionBillUserMap)
                    .build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get all publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    /**
     * Get all information about publication's themes and types
     * @see FullPublicationInfoWrapper
     * @return wrapper object with publication's information or null
     * @throws DataBaseWorkException errors in DAO layer
     */
    public FullPublicationInfoWrapper getPubThemesAndTypes() {
        Connection connection = ConnectionPool.getConnection(true);
        FullPublicationInfoWrapper wrapper = null;
        try {
            List<PublicationType> publicationTypeList = publicationTypeDao.getAll(connection);
            List<PublicationTheme> publicationThemeList = publicationThemeDao.getAll(connection);
            wrapper = new FullPublicationInfoWrapper.Builder()
                    .setPublicationTypeList(publicationTypeList)
                    .setPublicationThemeList(publicationThemeList)
                    .build();
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get publication themes and types", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return wrapper;
    }

    /**
     * Get all selected publications by type, theme and status
     * @param pubTypeId publication's type id number
     * @param pubThemeId publication's theme id number
     * @param pubStatusId publication's status id number
     * @return list of selected publications
     * @throws DataBaseWorkException errors in DAO layer
     */
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

    /**
     * Get all selected publications by type, theme and status. Auxiliary sort method
     * @param pubTypeId publication's type id number
     * @param pubThemeId publication's theme id number
     * @param pubStatusId publication's status id number
     * @return list of selected publications
     * @throws DataBaseWorkException errors in DAO layer
     */
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

    /**
     * Get all selected publications by type, theme
     * @param pubTypeId publication's type id number
     * @param pubThemeId publication's theme id number
     * @return list of selected publications
     * @throws DataBaseWorkException errors in DAO layer
     */
    public List<Publication> selectPublicationsByTypeByTheme(int pubTypeId, int pubThemeId) {
        Connection connection = ConnectionPool.getConnection(true);
        List<Publication> publicationList = null;
        try {
            publicationList = supportGetPubList(connection, pubTypeId, pubThemeId, 1);
        } catch (DataBaseWorkException e) {
            LOGGER.error("Can't get selected publications", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return publicationList;
    }

    /**
     * Get publications by type, theme and status with information about subscription options
     * @param pubTypeId publication's type id number
     * @param pubThemeId publication's theme id number
     * @param pubStatusId publication's status id number
     * @return map of selected publications with information about subscription options
     * @throws DataBaseWorkException errors in DAO layer
     */
    public Map<Publication, List<PublicationPeriodicyCost>> getPublicationWithCosts(int pubTypeId, int pubThemeId, int pubStatusId) {
        Connection connection = ConnectionPool.getConnection(true);
        Map<Publication, List<PublicationPeriodicyCost>> publicationListMap = new LinkedHashMap<>();
        List<PublicationPeriodicyCost> publicationPeriodicyCosts = null;

        try {
            List<Publication> publications = supportGetPubList(connection, pubTypeId, pubThemeId, pubStatusId);
            for (int i = 0; i < publications.size(); i++) {
                publicationPeriodicyCosts = publicationPeriodicityCostDao.getAllByPubId(connection, publications.get(i).getId());
                publicationListMap.put(publications.get(i), publicationPeriodicyCosts);
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
