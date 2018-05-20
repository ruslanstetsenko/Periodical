package dao;

import beans.LivingAddress;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LivingAddressDao {

    public void create(String region, String district, String city, String street, String building, String appartment, Connection connection);
    public LivingAddress read(int id, Connection connection);
    public void update(int addressId, String region, String district, String city, String street, String building, String appartment, Connection connection);
    public void delete(LivingAddress livingAddress, Connection connection) throws SQLException;
    public List<LivingAddress> getAll(Connection connection) throws SQLException;
    public int getLastId(Connection connection);

}
