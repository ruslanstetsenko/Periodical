package dao.implementations;

import beans.PublicationPeriodicyCost;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.PublicationPeriodicityCostDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationPeriodicityCostDaoImpl implements PublicationPeriodicityCostDao {
    private static final Logger logger = LogManager.getLogger(PublicationPeriodicityCostDaoImpl.class);

    @Override
    public void create(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) {
        String sql = "INSERT INTO publication_periodicity_cost (times_per_year, cost, publication_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicyCost.getTimesPerYear());
            preparedStatement.setDouble(2, publicationPeriodicyCost.getCost());
            preparedStatement.setInt(3, publicationPeriodicyCost.getPublicationId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can't create periodicy cost in DB", e.getCause());
        }

    }

    @Override
    public PublicationPeriodicyCost read(int id, Connection connection) {
        PublicationPeriodicyCost publicationPeriodicyCost = new PublicationPeriodicyCost();
        String sql = "SELECT * FROM publication_periodicity_cost WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            publicationPeriodicyCost.setId(id);
            publicationPeriodicyCost.setTimesPerYear(resultSet.getInt("times_per_year"));
            publicationPeriodicyCost.setCost(resultSet.getDouble("cost"));
            publicationPeriodicyCost.setPublicationId(resultSet.getInt("publication_id"));
        } catch (SQLException e) {
            logger.error("Can't read periodicy cost from DB", e.getCause());
        }
        return publicationPeriodicyCost;
    }

//    @Override
//    public PublicationPeriodicyCost readByPubId(int pubId, Connection connection) throws SQLException {
//        PublicationPeriodicyCost publicationPeriodicityCost = new PublicationPeriodicyCost();
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
    public void update(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) {
        String sql = "UPDATE publication_periodicity_cost SET times_per_year=?, cost=?, publication_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicyCost.getTimesPerYear());
            preparedStatement.setDouble(2, publicationPeriodicyCost.getCost());
            preparedStatement.setInt(3, publicationPeriodicyCost.getPublicationId());
            preparedStatement.setInt(4, publicationPeriodicyCost.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update periodicy cost in DB", e.getCause());
        }
    }

    @Override
    public void delete(PublicationPeriodicyCost publicationPeriodicyCost, Connection connection) {
        String sql = "DELETE FROM publication_periodicity_cost WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, publicationPeriodicyCost.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete periodicy cost in DB", e.getCause());
        }
    }

    @Override
    public List<PublicationPeriodicyCost> getAll(Connection connection) {
        List<PublicationPeriodicyCost> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_periodicity_cost ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new PublicationPeriodicyCost.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTimesPerYear(resultSet.getInt("times_per_year"))
                        .setCost(resultSet.getDouble("cost"))
                        .setPublicationId(resultSet.getInt("publication_id"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get periodicy costs from DB", e.getCause());
        }
        return list;
    }

    @Override
    public List<PublicationPeriodicyCost> getAllByPubId(Connection connection, int pubId) {
        List<PublicationPeriodicyCost> list = new ArrayList<>();
        String sql = "SELECT * FROM publication_periodicity_cost WHERE publication_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pubId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new PublicationPeriodicyCost.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTimesPerYear(resultSet.getInt("times_per_year"))
                        .setCost(resultSet.getDouble("cost"))
                        .setPublicationId(pubId)
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get periodicy costs selected by publication from DB", e.getCause());
        }
        return list;
    }
}
