package dao;

import beens.LivingAddress;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LivingAddressDao {

    public void create(LivingAddress livingAddress, Connection connection) throws SQLException;
    public LivingAddress read(int id, Connection connection) throws SQLException;
    public void update(LivingAddress livingAddress, Connection connection) throws SQLException;
    public void delete(LivingAddress livingAddress, Connection connection) throws SQLException;
    public List<LivingAddress> getAll(Connection connection) throws SQLException;

}
