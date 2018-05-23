package dao.interfaces;

import beans.LivingAddress;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LivingAddressDao {

    void create(String region, String district, String city, String street, String building, String appartment, Connection connection);
    LivingAddress read(int id, Connection connection);
    void update(int addressId, String region, String district, String city, String street, String building, String appartment, Connection connection);
    void delete(LivingAddress livingAddress, Connection connection) ;
    List<LivingAddress> getAll(Connection connection) ;
    int getLastId(Connection connection);

}
