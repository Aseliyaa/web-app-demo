package com.example.demo.command;

import com.example.demo.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
