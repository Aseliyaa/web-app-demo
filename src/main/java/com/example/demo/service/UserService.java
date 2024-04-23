package com.example.demo.service;

import com.example.demo.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean register(String userName, String login, String password, String repeatPassword, String checkboxValue) throws ServiceException;
}
