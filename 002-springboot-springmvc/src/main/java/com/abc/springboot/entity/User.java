package com.abc.springboot.entity;

public class User {
    private int userId;
    private String userName;
    private String userGender;

    public User() {
    }

    public User(int userId, String userName, String userGender) {
        this.userId = userId;
        this.userName = userName;
        this.userGender = userGender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                '}';
    }
}
