package dao.implementations;

import beans.PassportIdentNumber;
import commands.CancelCreatePublicationCommand;
import dao.interfaces.PassportIdentNumberDao;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportIdentNumberDaoImpl implements PassportIdentNumberDao {
    private static final Logger logger = LogManager.getLogger(PassportIdentNumberDaoImpl.class);

    @Override
    public int create(String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, Connection connection) {
        String sql = "INSERT INTO passport_ident_number (serial, number, date_of_issue, issued_by, id_number) VALUES (?, ?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passportSerial);
            preparedStatement.setInt(2, passportNumber);
            preparedStatement.setDate(3, passportDateOfIssue);
            preparedStatement.setString(4, passportIssuedBy);
            preparedStatement.setString(5, identNuber);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create user passport info in DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
        return id;
    }

    @Override
    public PassportIdentNumber read(int id, Connection connection) {
        PassportIdentNumber passportIdentNumber = new PassportIdentNumber();
        String sql = "SELECT * FROM passport_ident_number WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passportIdentNumber.setId(id);
                passportIdentNumber.setSerial(resultSet.getString("serial"));
                passportIdentNumber.setNumber(resultSet.getInt("number"));
                passportIdentNumber.setDateOfIssue(resultSet.getDate("date_of_issue"));
                passportIdentNumber.setIssuedBy(resultSet.getString("issued_by"));
                passportIdentNumber.setIdNumber(resultSet.getString("id_number"));
            }
        } catch (SQLException e) {
            logger.error("Can't read user passport info from DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
        return passportIdentNumber;
    }

    @Override
    public void update(int passportId, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, Connection connection) {
        String sql = "UPDATE passport_ident_number SET serial=?, number=?, date_of_issue=?, issued_by=?, id_number=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passportSerial);
            preparedStatement.setInt(2, passportNumber);
            preparedStatement.setDate(3, passportDateOfIssue);
            preparedStatement.setString(4, passportIssuedBy);
            preparedStatement.setString(5, identNuber);
            preparedStatement.setInt(6, passportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user passport info in DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
    }

    @Override
    public void delete(PassportIdentNumber passportIdentNumber, Connection connection) {
        String sql = "DELETE FROM passport_ident_number WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, passportIdentNumber.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user passport info in DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
    }

    @Override
    public List<PassportIdentNumber> getAll(Connection connection) {
        List<PassportIdentNumber> list = new ArrayList<>();
        String sql = "SELECT * FROM passport_ident_number ORDER BY id";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new PassportIdentNumber.Builder()
                        .setId(resultSet.getInt("id"))
                        .setSerial(resultSet.getString("serial"))
                        .setNumber(resultSet.getInt("number"))
                        .setDateOfIssue(resultSet.getDate("date_of_issue"))
                        .setIssuedBy(resultSet.getString("issued_by"))
                        .setIdNumber(resultSet.getString("id_number"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Can't get user passport dataset in DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
        return list;
    }

    @Override
    public int getLastId(Connection connection) {
        int id = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS lastId FROM passport_ident_number");
            if (resultSet.next()) {
                id = resultSet.getInt("lastId");
            }
        } catch (SQLException e) {
            logger.error("Can't get last added user passport info from DB", e.getCause());
            throw new DataBaseWorkException("message.error.passport");
        }
        return id;
    }
}
