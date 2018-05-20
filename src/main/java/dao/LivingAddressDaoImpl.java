package dao;

import beans.LivingAddress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivingAddressDaoImpl implements LivingAddressDao {

    @Override
    public void create(String region, String district, String city, String street, String building, String appartment, Connection connection) {
        String sql = "INSERT INTO living_address (region, district, city, street, building, appartment) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, region);
            preparedStatement.setString(2, district);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, building);
            preparedStatement.setString(6, appartment);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LivingAddress read(int id, Connection connection) {
        LivingAddress livingAddress = new LivingAddress();
        String sql = "SELECT * FROM living_address WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            livingAddress.setId(id);
            livingAddress.setRegion(resultSet.getString("region"));
            livingAddress.setDistrict(resultSet.getString("district"));
            livingAddress.setCity(resultSet.getString("city"));
            livingAddress.setStreet(resultSet.getString("street"));
            livingAddress.setBuilding(resultSet.getString("building"));
            livingAddress.setAppartment(resultSet.getString("appartment"));
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(LivingAddress livingAddress, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM living_address WHERE id=?");
        preparedStatement.setInt(1, livingAddress.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<LivingAddress> getAll(Connection connection) throws SQLException {
        List<LivingAddress> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM living_address ORDER BY id");

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
        return list;    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS lastId FROM living_address");
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
