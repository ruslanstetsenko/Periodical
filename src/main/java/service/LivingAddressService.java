package service;

import dao.DaoFactory;
import dao.LivingAddressDao;
import entity.LivingAddress;

import java.sql.Connection;
import java.sql.SQLException;

public class LivingAddressService {

    private LivingAddressDao livingAddressDao = DaoFactory.getLivingAddressDao();

    public void create(LivingAddress livingAddress, Connection connection) {
        try {
            livingAddressDao.create(livingAddress, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LivingAddress read(int id, Connection connection) {
        LivingAddress livingAddress = new LivingAddress();
        try {
            livingAddress = livingAddressDao.read(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livingAddress;
    }

    public void update(LivingAddress livingAddress, Connection connection) {
        try {
            livingAddressDao.update(livingAddress, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
