package com.example.demo.service.impl;


import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.validator.LoginValidator;
import com.example.demo.validator.PasswordValidator;
import com.example.demo.validator.UsernameValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    public static String errorMessage;
    Logger logger = LogManager.getLogger();

    public static String getErrorMessage() {
        return errorMessage;
    }

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }


    @Override
    public boolean register(String userName, String login, String password, String repeatPassword, String checkboxValue) throws ServiceException {
        boolean match = false;
        errorMessage = "";
        if (PasswordValidator.isValid(password) && password.equals(repeatPassword)
                && LoginValidator.isValid(login) && UsernameValidator.isValid(userName)
                && checkboxValue != null) {
            logger.log(Level.INFO, "+++++++++++> Data Is Valid");
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            try {
                match = userDao.register(userName, login, password);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else if (!UsernameValidator.isValid(userName)) {
            logger.log(Level.INFO, "-----------> Username Is Not Valid");
            errorMessage = UsernameValidator.getMessage();
        } else if (!LoginValidator.isValid(login)) {
            logger.log(Level.INFO, "-----------> Login Is Not Valid");
            errorMessage = LoginValidator.getMessage();
        } else if (!PasswordValidator.isValid(password)) {
            logger.log(Level.INFO, "-----------> Password Is Not Valid");
            errorMessage = PasswordValidator.getMessage();
        } else if (!password.equals(repeatPassword)) {
            logger.log(Level.INFO, "-----------> Password != Repeat Password");
            errorMessage = "Repeat password correctly";
        } else if(checkboxValue == null){
            logger.log(Level.INFO, "-----------> Checkbox Value is null");
            errorMessage = "Click on checkbox";
        } else {
            logger.log(Level.INFO, "-----------> Error in system");
            errorMessage = "Error in system";
        }
        return match;
    }
}
