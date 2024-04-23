package com.example.demo.validator;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

public class UsernameValidator {
    private static String message = "";
    private static final Logger logger = LogManager.getLogger();
    private static final String regexPattern = "^[a-zA-Z][a-zA-Z0-9_]{6,19}$";
    public static String getMessage() {
        return message;
    }

    public static boolean isValid(String userName){
        boolean isValid = false;

        try {
            boolean isInDb = isInDb(userName);
            message = "";
            if(isUsernameValidFormat(userName) && !isInDb){
                logger.log(Level.INFO, "---------> Username Is Valid");
                isValid = true;
            } else if (isInDb) {
                message = "This username is already exists";
                logger.log(Level.INFO, "---------> Username Is In DB");
            } else {
                message = "Your username is incorrect";
                logger.log(Level.INFO, "---------> Username Is Incorrect");
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return isValid;
    }
    private static boolean isUsernameValidFormat(String userName){
        return Pattern.compile(regexPattern)
                .matcher(userName)
                .matches();
    }

    private static boolean isInDb(String userName) throws DaoException {
        List<User> users;
        boolean isInDb = false;
        try {
            users = UserDaoImpl.getInstance().findAll();
            for (User user: users){
                if (user.userName.equals(userName)) {
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
