package dao;

import beans.PassportIdentNumber;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PassportIdentNumberDao {

    public void create(String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection);

    public PassportIdentNumber read(int id, Connection connection);

    public void update(int passportId, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection);

    public void delete(PassportIdentNumber passportIdentNumber, Connection connection) throws SQLException;

    public List<PassportIdentNumber> getAll(Connection connection) throws SQLException;

    public int getLastId(Connection connection);

}


