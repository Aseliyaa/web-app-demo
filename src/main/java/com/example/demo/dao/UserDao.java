package com.example.demo.dao;

import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
    boolean register(String userName, String login, String password) throws DaoException;
}
