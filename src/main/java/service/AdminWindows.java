package service;

import connection.ConnectionPool;
import dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class AdminWindows {

    private PublicationDao publicationDao = DaoFactory.getPublicationDao();
    private PublicationTypeDao publicationTypeDao = DaoFactory.getPublicationTypeDao();
    private PublicationThemeDao publicationThemeDao = DaoFactory.getPublicationThemeDao();
    private PublicationPeriodicityCostDao publicationPeriodicityCostDao = DaoFactory.getPublicationPeriodicityCostDao();
    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();
    private SubscriptionDao subscriptionDao = DaoFactory.getSubscriptionDao();
    private UserDao userDao = DaoFactory.getUserDao();

    private List<Publication> publicationList;
    private List<PublicationPeriodicityCost> publicationPeriodicityCostList;
    private List<SubscriptionBill> subscriptionBillList;

    private int currentPubStatusId = 1;
    private int currentPubTypeId = 1;
    private int currentPubThemeId = 1;
    private int currentBillStatus = 1;

    private int publicationsAmount;
    private int subscriptionBillAmount;


    public void loadAdminWindow(Connection connection) {
        try {
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication -> publication.getPublicationTypeId() == currentPubTypeId)
                    .filter(publication -> publication.getPublicationThemeId() == currentPubThemeId)
                    .collect(Collectors.toList());
            publicationsAmount = publicationList.size();
            subscriptionBillList = subscriptionBillDao.getAll(connection)
                    .stream()
                    .filter(subscriptionBill -> subscriptionBill.getPaid() == currentBillStatus)
                    .collect(Collectors.toList());
            subscriptionBillAmount = subscriptionBillList.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Publication> selectPublicationsByStatus(PublicationStatus status) {
        Connection connection = ConnectionPool.getConnection();
        publicationList = null;
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

    public List<Publication> selectPublicationsByTheme(PublicationTheme theme) {
        Connection connection = ConnectionPool.getConnection();
        publicationList = null;
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

    public List<Publication> selectPublicationsByType(PublicationType type) {
        Connection connection = ConnectionPool.getConnection();
        publicationList = null;
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

    public void addNewPublication(String name, int issnNumber, LocalDate registrationDate, String website, Integer publicationTypeId, Integer publicationStatusId, Integer publicationThemeId) {
        Connection connection = ConnectionPool.getConnection();
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

    public void updatePublication(String name, int issnNumber, LocalDate registrationDate, String website, Integer publicationTypeId, Integer publicationStatusId, Integer publicationThemeId) {
        Connection connection = ConnectionPool.getConnection();
        Publication publication = new Publication.Builder()
                .setName(name)
                .setIssnNumber(issnNumber)
                .setRegistrationDate(registrationDate)
                .setWebsite(website)
                .setPublicationTypeId(publicationTypeId)
                .setPublicationStatusId(publicationStatusId)
                .build();
        try {
            publicationDao.update(publication, connection);
            connection.commit();
            publicationList = publicationDao.getAll(connection)
                    .stream()
                    .filter(publication1 -> publication.getPublicationStatusId() == currentPubStatusId)
                    .filter(publication1 -> publication.getPublicationTypeId() == currentPubTypeId)
                    .filter(publication1 -> publication.getPublicationThemeId() == currentPubThemeId)
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

    public void deletePublication(int publicationId) {
        Connection connection = ConnectionPool.getConnection();
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
        Connection connection = ConnectionPool.getConnection();
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
        Connection connection = ConnectionPool.getConnection();
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

    public void deleteSubscriptionBill(SubscriptionBill subscriptionBill) {
        LocalDate setBill = LocalDate.ofInstant(subscriptionBill.getBillSetDay().toInstant(), ZoneId.systemDefault());
        if (subscriptionBill.getValidityPeriod() >
                Period.between(setBill, LocalDate.now()).getDays()) {
            Connection connection = ConnectionPool.getConnection();
            try {
                subscriptionBillDao.delete(subscriptionBill.getId(), connection);
                connection.commit();
                subscriptionBillList = subscriptionBillDao.getAll(connection)
                        .stream()
                        .filter(subscriptionBill1 -> subscriptionBill1.getPaid() == currentBillStatus)
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
        } else {
            //cant delete
        }

    }


}
