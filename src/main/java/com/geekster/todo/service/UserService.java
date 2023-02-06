package com.geekster.todo.service;

import com.geekster.todo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    static List<User> userList = new ArrayList<>();

    public String saveUser(User user) {

        this.userList.add(user);
        return "Saved user with name- " + user.getFirstName();
    }

    public List<User> getUser(String fistName, String userName) {
        List<User> requiredUser = new ArrayList<>();

        if (null != fistName) {
            for (User user : userList) {
                if(user.getFirstName().equals(fistName)) {
                    requiredUser.add(user);
                    return requiredUser;
                }
            }
        } else if (null != userName) {
            for (User user : userList) {
                if(user.getUsername().equals(userName)) {
                    requiredUser.add(user);
                    return requiredUser;
                }
            }
        } else {
            return userList;
        }
        return requiredUser;
    }
}
