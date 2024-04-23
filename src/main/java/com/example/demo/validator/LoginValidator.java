package com.example.demo.validator;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class LoginValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static String message = "";

    public static String getMessage() {
        return message;
    }

    public static boolean isValid(String login) {
        boolean isValid = false;

        try {
            boolean isInDb = isInDb(login);
            message = "";
            if (isLoginValidFormat(login) && !isInDb) {
                isValid = true;
                logger.log(Level.INFO, "---------> Login Is Valid");
            } else if (isInDb) {
                message = "This login is already exists";
                logger.log(Level.INFO, "---------> Login Is In DB");
            } else {
                message = "Your email address is incorrect";
                logger.log(Level.INFO, "---------> Login Is Incorrect");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return isValid;
    }

    private static boolean isLoginValidFormat(String login){
        return Pattern.compile(regexPattern)
                .matcher(login)
                .matches();
    }

    private static boolean isInDb(String login) throws DaoException {
        List<User> users;
        boolean isInDb = false;
        try {
            users = UserDaoImpl.getInstance().findAll();
            for (User user: users){
                if (user.login.equals(login)) {
                    isInDb = true;
                    break;
                }
            }
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return isInDb;
    }
}
