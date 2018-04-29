package dao;

import entity.LivingAddress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivingAddressDaoImpl implements LivingAddressDao {

    @Override
    public void create(LivingAddress livingAddress, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO living_address (region, district, city, street, building, appartment) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, livingAddress.getRegion());
        preparedStatement.setString(2, livingAddress.getDistrict());
        preparedStatement.setString(3, livingAddress.getCity());
        preparedStatement.setString(4, livingAddress.getStreet());
        preparedStatement.setString(5, livingAddress.getBuilding());
        preparedStatement.setString(6, livingAddress.getAppartment());
        preparedStatement.execute();
    }

    @Override
    public LivingAddress read(int id, Connection connection) throws SQLException {
        LivingAddress livingAddress = new LivingAddress();

        PreparedStatement preparedStatement = connection.prepareStatement("select region, district, city, street, building, appartment from living_address where id=?");
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

        return livingAddress;
    }

    @Override
    public void update(LivingAddress livingAddress, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE living_address SET region=?, district=?, city=?, street=?, building=?, appartment=? WHERE id=?");
        preparedStatement.setString(1, livingAddress.getRegion());
        preparedStatement.setString(2, livingAddress.getDistrict());
        preparedStatement.setString(3, livingAddress.getCity());
        preparedStatement.setString(4, livingAddress.getStreet());
        preparedStatement.setString(5, livingAddress.getBuilding());
        preparedStatement.setString(6, livingAddress.getAppartment());
        preparedStatement.setInt(7, livingAddress.getId());
        preparedStatement.executeUpdate();
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
}
