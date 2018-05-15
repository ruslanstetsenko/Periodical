package service;

import connection.ConnectionPool;
import dao.*;
import beens.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminWindowsService {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();
    private PublicationStatusDao publicationStatusDao = DaoFactory.getPublicationStatusDao();

    private List<PublicationPeriodicityCost> publicationPeriodicityCostList;
    private List<User> userList;

    private int publicationsAmount;
    private int subscriptionBillAmount;


    public List[] loadAdminWindow() {
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

    public List[] selectedloadAdminWindow(int pubTypeId, int pubThemeId, int pubStatusId, int billStatusId) {
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
            publicationsAmount = publicationList.size();
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
            publicationsAmount = publicationList.size();
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
            publicationsAmount = publicationList.size();
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
            publicationsAmount = publicationList.size();
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
            publicationsAmount = publicationList.size();
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

    public List<SubscriptionBill> selectBillsByStatus(byte status) {
        Connection connection = ConnectionPool.getConnection(true);
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        subscriptionBillList = null;
        try {
            subscriptionBillList = subscriptionBillDao.getAll(connection)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == status)
                    .collect(Collectors.toList());
            subscriptionBillAmount = subscriptionBillList.size();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subscriptionBillList;
    }

    public void showAboutSubscrBill(SubscriptionBill subscriptionBill) {
        Connection connection = ConnectionPool.getConnection(true);
        try {
            int userId = subscriptionDao.readByBill(subscriptionBill.getId(), connection).getUsersId();
            User user = userDao.read(userId, connection);
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

    public void deleteSubscriptionBill(SubscriptionBill subscriptionBill, int currentBillStatus) {
        LocalDate setBill = LocalDate.ofInstant(subscriptionBill.getBillSetDay().toInstant(), ZoneId.systemDefault());
        if (subscriptionBill.getValidityPeriod() >
                Period.between(setBill, LocalDate.now()).getDays()) {
            Connection connection = ConnectionPool.getConnection(false);
            List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
            try {
                subscriptionBillDao.delete(subscriptionBill.getId(), connection);
                connection.commit();
                subscriptionBillList = subscriptionBillDao.getAll(connection)
                        .stream()
                        .filter(subscriptionBill1 -> subscriptionBill1.getPaid() == currentBillStatus)
                        .collect(Collectors.toList());
//                publicationsAmount = publicationList.size();
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
        } else {
            //cant delete
        }

    }


}
