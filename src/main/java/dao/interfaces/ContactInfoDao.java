package dao.interfaces;

import beans.ContactInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContactInfoDao {

    void create(String userPhoneNumber, String userEmail, Connection connection);
    ContactInfo read(int id, Connection connection);
    void update(int contactInfoId, String userPhoneNumber, String userEmail, Connection connection);
    void delete(ContactInfo contactInfo, Connection connection);
    List<ContactInfo> getAll(Connection connection);
    int getLastId(Connection connection);
}
