package service;

import dao.ContactInfoDao;
import dao.DaoFactory;

public class ContactInfoService {

    private ContactInfoDao contactInfoDao = DaoFactory.getContactInfoDao();

//    public void create(ContactInfo contactInfo, Connection connection) {
//        try {
//            contactInfoDao.create(contactInfo, connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ContactInfo read(int id, Connection connection) {
//        ContactInfo contactInfo = new ContactInfo();
//        try {
//            contactInfo = contactInfoDao.read(id, connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return contactInfo;
//    }
//
//    public void update(ContactInfo contactInfo, Connection connection) {
//        try {
//            contactInfoDao.update(contactInfo, connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
