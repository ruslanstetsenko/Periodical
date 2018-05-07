package dao;

import beens.PassportIdentNumber;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PassportIdentNumberDao {

    public void create(PassportIdentNumber passportIdentNumber, Connection connection) throws SQLException;
    public PassportIdentNumber read(int id, Connection connection) throws SQLException;
    public void update(PassportIdentNumber passportIdentNumber, Connection connection) throws SQLException;
    public void delete(PassportIdentNumber passportIdentNumber, Connection connection) throws SQLException;
    public List<PassportIdentNumber> getAll(Connection connection) throws SQLException;

}
