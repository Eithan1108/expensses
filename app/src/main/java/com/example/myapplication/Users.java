package com.example.myapplication;

import java.util.List;

public class Users {
    private String userName;
    private String userEmail;
    private String userPicName;
    private String userPass;
    private List<Category> faveCategory;
    private List<Expensse> userExpensses;
    private List<Stats> userStats;

    public Users() {
    }

    public Users(String userName, String userEmail, String userPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPicName() {
        return userPicName;
    }

    public void setUserPicName(String userPicName) {
        this.userPicName = userPicName;
    }
}
