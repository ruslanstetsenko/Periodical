package dao;

import beans.ContactInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContactInfoDao {

    public void create(String userPhoneNumber, String userEmail, Connection connection);
    public ContactInfo read(int id, Connection connection);
    public void update(int contactInfoId, String userPhoneNumber, String userEmail, Connection connection);
    public void delete(ContactInfo contactInfo, Connection connection) throws SQLException;
    public List<ContactInfo> getAll(Connection connection) throws SQLException;
    public int getLastId(Connection connection);
}
