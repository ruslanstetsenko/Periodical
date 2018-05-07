package dao;

import beens.ContactInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContactInfoDao {

    public void create(ContactInfo contactInfo, Connection connection) throws SQLException;
    public ContactInfo read(int id, Connection connection) throws SQLException;
    public void update(ContactInfo contactInfo, Connection connection) throws SQLException;
    public void delete(ContactInfo contactInfo, Connection connection) throws SQLException;
    public List<ContactInfo> getAll(Connection connection) throws SQLException;
}
