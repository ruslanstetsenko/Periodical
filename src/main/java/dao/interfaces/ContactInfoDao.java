package dao.interfaces;

import beans.ContactInfo;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContactInfoDao {

    int create(String userPhoneNumber, String userEmail, Connection connection) throws DataBaseWorkException;
    ContactInfo read(int id, Connection connection) throws DataBaseWorkException;
    void update(int contactInfoId, String userPhoneNumber, String userEmail, Connection connection) throws DataBaseWorkException;
    void delete(ContactInfo contactInfo, Connection connection) throws DataBaseWorkException;
    List<ContactInfo> getAll(Connection connection) throws DataBaseWorkException;
//    int getLastId(Connection connection) throws DataBaseWorkException;
}
