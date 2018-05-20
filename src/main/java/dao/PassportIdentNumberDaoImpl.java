package dao;

import beans.PassportIdentNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportIdentNumberDaoImpl implements PassportIdentNumberDao {

    @Override
    public void create(String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection) {
        String sql = "INSERT INTO passport_ident_number (serial, number, date_of_issue, issued_by, id_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passportSerial);
            preparedStatement.setInt(2, passportNumber);
            preparedStatement.setDate(3, passportDateOfIssue);
            preparedStatement.setString(4, passportIssuedBy);
            preparedStatement.setInt(5, identNuber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PassportIdentNumber read(int id, Connection connection) {
        PassportIdentNumber passportIdentNumber = new PassportIdentNumber();
        String sql = "SELECT * FROM passport_ident_number WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            passportIdentNumber.setId(id);
            passportIdentNumber.setSerial(resultSet.getString("serial"));
            passportIdentNumber.setNumber(resultSet.getInt("number"));
            passportIdentNumber.setDateOfIssue(resultSet.getDate("date_of_issue"));
            passportIdentNumber.setIssuedBy(resultSet.getString("issued_by"));
            passportIdentNumber.setIdNumber(resultSet.getInt("id_number"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passportIdentNumber;
    }

    @Override
    public void update(int passportId, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection) {
        String sql = "UPDATE passport_ident_number SET serial=?, number=?, date_of_issue=?, issued_by=?, id_number=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passportSerial);
            preparedStatement.setInt(2, passportNumber);
            preparedStatement.setDate(3, passportDateOfIssue);
            preparedStatement.setString(4, passportIssuedBy);
            preparedStatement.setInt(5, identNuber);
            preparedStatement.setInt(6, passportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(PassportIdentNumber passportIdentNumber, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM passport_ident_number WHERE id=?");
        preparedStatement.setInt(1, passportIdentNumber.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<PassportIdentNumber> getAll(Connection connection) throws SQLException {
        List<PassportIdentNumber> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM passport_ident_number ORDER BY id");

        while (resultSet.next()) {
            list.add(new PassportIdentNumber.Builder()
                    .setId(resultSet.getInt("id"))
                    .setSerial(resultSet.getString("serial"))
                    .setNumber(resultSet.getInt("number"))
                    .setDateOfIssue(resultSet.getDate("date_of_issue"))
                    .setIssuedBy(resultSet.getString("issued_by"))
                    .setIdNumber(resultSet.getInt("id_number"))
                    .build());
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
            e.printStackTrace();
        }
        return id;
    }
}
