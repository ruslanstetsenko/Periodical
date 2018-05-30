package dao.implementations;

import beans.LivingAddress;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.LivingAddressDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivingAddressDaoImpl implements LivingAddressDao {
    private static final Logger logger = LogManager.getLogger(LivingAddressDaoImpl.class);

    @Override
    public int create(String region, String district, String city, String street, String building, String appartment, Connection connection) {
        String sql = "INSERT INTO living_address (region, district, city, street, building, appartment) VALUES (?, ?, ?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, region);
            preparedStatement.setString(2, district);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, building);
            preparedStatement.setString(6, appartment);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create user address in DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }
        return id;
    }

    @Override
    public LivingAddress read(int id, Connection connection) {
        LivingAddress livingAddress = new LivingAddress();
        String sql = "SELECT * FROM living_address WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                livingAddress.setId(id);
                livingAddress.setRegion(resultSet.getString("region"));
                livingAddress.setDistrict(resultSet.getString("district"));
                livingAddress.setCity(resultSet.getString("city"));
                livingAddress.setStreet(resultSet.getString("street"));
                livingAddress.setBuilding(resultSet.getString("building"));
                livingAddress.setAppartment(resultSet.getString("appartment"));
            }
        } catch (SQLException e) {
            logger.error("Can't read user address from DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }

        return livingAddress;
    }

    @Override
    public void update(int addressId, String region, String district, String city, String street, String building, String appartment, Connection connection) {
        String sql = "UPDATE living_address SET region=?, district=?, city=?, street=?, building=?, appartment=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, region);
            preparedStatement.setString(2, district);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, building);
            preparedStatement.setString(6, appartment);
            preparedStatement.setInt(7, addressId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user address in DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }
    }

    @Override
    public void delete(LivingAddress livingAddress, Connection connection) {
        String sql = "DELETE FROM living_address WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, livingAddress.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user address in DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }
    }

    @Override
    public List<LivingAddress> getAll(Connection connection) {
        List<LivingAddress> list = new ArrayList<>();
        String sql = "SELECT * FROM living_address ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new LivingAddress.Builder()
                        .setId(resultSet.getInt("id"))
                        .setRegion(resultSet.getString("region"))
                        .setDistrict(resultSet.getString("district"))
                        .setCity(resultSet.getString("city"))
                        .setStreet(resultSet.getString("street"))
                        .setBuilding(resultSet.getString("building"))
                        .setAppartment(resultSet.getString("appartment"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get user addresses from DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }
        return list;
    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS lastId FROM living_address");
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            logger.error("Can't get last added user address from DB", e.getCause());
            throw new DataBaseWorkException("message.error.address");
        }
        return id;
    }
}
