package dao.interfaces;

import beans.PassportIdentNumber;
import exceptions.DataBaseWorkException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PassportIdentNumberDao {

    int create(String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, Connection connection) throws DataBaseWorkException;

    PassportIdentNumber read(int id, Connection connection) throws DataBaseWorkException;

    void update(int passportId, String passportSerial, int passportNumber, Date passportDateOfIssue, String passportIssuedBy, String identNuber, Connection connection) throws DataBaseWorkException;

    void delete(PassportIdentNumber passportIdentNumber, Connection connection) throws DataBaseWorkException;

    List<PassportIdentNumber> getAll(Connection connection) throws DataBaseWorkException;

    int getLastId(Connection connection) throws DataBaseWorkException;

}


