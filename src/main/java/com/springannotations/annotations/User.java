package com.springannotations.annotations;


import org.springframework.beans.factory.annotation.Autowired;

public class User {


    private String userName;

    public User(String name) {
        this.userName = name;
    }

    public void printUserData() {
        System.out.println("This is user data for username- " + this.userName);

    }
}
