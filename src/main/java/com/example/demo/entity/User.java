package com.example.demo.entity;

public class User extends AbstractEntity {
    public String userName;
    public String login;
    public String password;

    public User(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }
}
