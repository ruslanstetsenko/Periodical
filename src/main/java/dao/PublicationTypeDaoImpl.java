package dao;

import entity.PublicationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationTypeDaoImpl implements PublicationTypeDao {

    @Override
    public void create(PublicationType publicationType, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publication_type (type_name) VALUES ?");
        preparedStatement.setString(1, publicationType.getTypeName());
        preparedStatement.execute();
    }

    @Override
    public PublicationType read(int id, Connection connection) throws SQLException {
        PublicationType publicationType = new PublicationType();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT type_name FROM publication_type WHERE id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        publicationType.setId(id);
        publicationType.setTypeName(resultSet.getString("type_name"));

        return publicationType;
    }

    @Override
    public void update(PublicationType publicationType, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publication_type SET type_name=? WHERE id=?");
        preparedStatement.setString(1, publicationType.getTypeName());
        preparedStatement.setInt(2, publicationType.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(PublicationType publicationType, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publication_type WHERE id=?");
        preparedStatement.setInt(1, publicationType.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<PublicationType> getAll(Connection connection) throws SQLException {
        List<PublicationType> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM publication_type ORDER BY id");

        while (resultSet.next()) {
            list.add(new PublicationType.Builder()
                    .setId(resultSet.getInt("id"))
                    .setTypeName(resultSet.getString("type_name"))
                    .build());
        }
        return list;
    }
}
