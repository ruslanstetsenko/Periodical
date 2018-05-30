package dao.interfaces;

import beans.LivingAddress;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LivingAddressDao {

    int create(String region, String district, String city, String street, String building, String appartment, Connection connection) throws DataBaseWorkException;
    LivingAddress read(int id, Connection connection) throws DataBaseWorkException;
    void update(int addressId, String region, String district, String city, String street, String building, String appartment, Connection connection) throws DataBaseWorkException;
    void delete(LivingAddress livingAddress, Connection connection) throws DataBaseWorkException;
    List<LivingAddress> getAll(Connection connection) throws DataBaseWorkException;
    int getLastId(Connection connection) throws DataBaseWorkException;

}
