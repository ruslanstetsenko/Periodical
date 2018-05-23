package dao.implementations;

import beans.PublicationPeriodicityCost;
import dao.interfaces.PublicationPeriodicityCostDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationPeriodicityCostDaoImpl implements PublicationPeriodicityCostDao {

    @Override
    public void create(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) {
        String sql = "INSERT INTO publication_periodicity_cost (times_per_year, cost, publication_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicityCost.getTimesPerYear());
            preparedStatement.setDouble(2, publicationPeriodicityCost.getCost());
            preparedStatement.setInt(3, publicationPeriodicityCost.getPublicationId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PublicationPeriodicityCost read(int id, Connection connection) {
        PublicationPeriodicityCost publicationPeriodicityCost = new PublicationPeriodicityCost();
        String sql = "SELECT * FROM publication_periodicity_cost WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            publicationPeriodicityCost.setId(id);
            publicationPeriodicityCost.setTimesPerYear(resultSet.getInt("times_per_year"));
            publicationPeriodicityCost.setCost(resultSet.getDouble("cost"));
            publicationPeriodicityCost.setPublicationId(resultSet.getInt("publication_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicationPeriodicityCost;
    }

//    @Override
//    public PublicationPeriodicityCost readByPubId(int pubId, Connection connection) throws SQLException {
//        PublicationPeriodicityCost publicationPeriodicityCost = new PublicationPeriodicityCost();
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM publication_periodicity_cost WHERE publication_id=?");
//        preparedStatement.setInt(1, pubId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//        publicationPeriodicityCost.setId(resultSet.getInt("id"));
//        publicationPeriodicityCost.setTimesPerYear(resultSet.getInt("times_per_year"));
//        publicationPeriodicityCost.setCost(resultSet.getDouble("cost"));
//        publicationPeriodicityCost.setPublicationId(pubId);
//
//        return publicationPeriodicityCost;
//    }

    @Override
    public void update(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) {
        String sql = "UPDATE publication_periodicity_cost SET times_per_year=?, cost=?, publication_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicityCost.getTimesPerYear());
            preparedStatement.setDouble(2, publicationPeriodicityCost.getCost());
            preparedStatement.setInt(3, publicationPeriodicityCost.getPublicationId());
            preparedStatement.setInt(4, publicationPeriodicityCost.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PublicationPeriodicityCost publicationPeriodicityCost, Connection connection) {
        String sql = "DELETE FROM publication_periodicity_cost WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicityCost.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PublicationPeriodicityCost> getAll(Connection connection) {
        List<PublicationPeriodicityCost> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_periodicity_cost ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new PublicationPeriodicityCost.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTimesPerYear(resultSet.getInt("times_per_year"))
                        .setCost(resultSet.getDouble("cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<PublicationPeriodicityCost> getAllByPubId(Connection connection, int pubId) {
        List<PublicationPeriodicityCost> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_periodicity_cost WHERE publication_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pubId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new PublicationPeriodicityCost.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTimesPerYear(resultSet.getInt("times_per_year"))
                        .setCost(resultSet.getDouble("cost"))
                        .setPublicationId(pubId)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
