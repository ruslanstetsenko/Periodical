package service;

import dao.DaoFactory;
import dao.PassportIdentNumberDao;
import entity.PassportIdentNumber;

import java.sql.Connection;
import java.sql.SQLException;

public class PassportIdentNumberService {

    private PassportIdentNumberDao passportIdentNumberDao = DaoFactory.getPassportIdentNumberDao();

    public void create(PassportIdentNumber passportIdentNumber, Connection connection) {
        try {
            passportIdentNumberDao.create(passportIdentNumber, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PassportIdentNumber read(int id, Connection connection) {
        PassportIdentNumber passportIdentNumber = new PassportIdentNumber();
        try {
            passportIdentNumber = passportIdentNumberDao.read(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passportIdentNumber;
    }

    public void update(PassportIdentNumber passportIdentNumber, Connection connection) {
        try {
            passportIdentNumberDao.update(passportIdentNumber, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
