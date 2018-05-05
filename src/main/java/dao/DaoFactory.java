package dao;

public class DaoFactory {

    public static AccountDao getAccountDao() {
        return new AccountDaoImpl();
    }

    public static ContactInfoDao getContactInfoDao() {
        return new ContactInfoDaoImpl();
    }

    public static LivingAddressDao getLivingAddressDao() {
        return new LivingAddressDaoImpl();
    }

    public static PassportIdentNumberDao getPassportIdentNumberDao() {
        return new PassportIdentNumberDaoImpl();
    }

    public static PublicationDao getPublicationDao() {
        return new PublicationDaoImpl();
    }

    public static PublicationPeriodicityCostDao getPublicationPeriodicityCostDao() {
        return new PublicationPeriodicityCostDaoImpl();
    }

    public static PublicationStatusDao getPublicationStatusDao() {
        return new PublicationStatusDaoImpl();
    }

    public static PublicationThemeDao getPublicationThemeDao() {
        return new PublicationThemeDaoImpl();
    }

    public static PublicationTypeDao getPublicationTypeDao() {
        return new PublicationTypeDaoImpl();
    }

    public static SubscriptionBillDao getSubscriptionBillDao() {
        return new SubscriptionBillDaoImpl();
    }

    public static SubscriptionDao getSubscriptionDao() {
        return new SubscriptionDaoImpl();
    }

    public static SubscriptionStatusDao getSubscriptionStatusDao() {
        return new SubscriptionStatusDaoImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static UserRoleDao getUserRoleDao() {
        return new UserRoleDaoImpl();
    }
}
