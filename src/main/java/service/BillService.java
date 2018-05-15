package service;

import dao.DaoFactory;
import dao.SubscriptionBillDao;

public class BillService {

    private SubscriptionBillDao subscriptionBillDao = DaoFactory.getSubscriptionBillDao();

}
