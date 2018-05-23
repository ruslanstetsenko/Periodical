package dao.interfaces;

import beans.PassportIdentNumber;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PassportIdentNumberDao {

    void create(String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection);

    PassportIdentNumber read(int id, Connection connection);

    void update(int passportId, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, int identNuber, Connection connection);

    void delete(PassportIdentNumber passportIdentNumber, Connection connection);

    List<PassportIdentNumber> getAll(Connection connection);

    int getLastId(Connection connection);

}


