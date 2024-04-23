package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static final String INSERT_USER = "INSERT INTO users (login, password, username) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static UserDaoImpl instance = new UserDaoImpl();
    Logger logger = LogManager.getLogger();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean isInsert = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.login);
            statement.setString(2, user.password);
            statement.setString(3, user.userName);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                isInsert = true;
                logger.log(Level.INFO, "+++++++++> Rows Inserted");
            } else {
                logger.log(Level.INFO, "+++++++++> Rows Not Inserted");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isInsert;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete unsupported");
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()){
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String userName = resultSet.getString(4);
                users.add(new User(userName, login, password));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }


    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                match = password.equals(passFromDb);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }

    @Override
    public boolean register(String userName, String login, String password) throws DaoException {
        User user = new User(userName, login, password);
        return insert(user);
    }

}
