package com.geekster.todo.service;

import com.geekster.todo.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    static List<Integer> userList = new ArrayList<>();

    static Map<Integer, User> userDataMap = new HashMap<>();

    public String saveUser(User user) {
        userDataMap.put(user.getUserId(), user);
        return "Saved user with name- " + user.getFirstName();
    }

    public List<User> getUser(Integer userId) {
        List<User> requiredUsersList = new ArrayList<>();
        if (null != userId) {
            if(userDataMap.containsKey(userId)) {
                requiredUsersList.add(userDataMap.get(userId));
            }
        } else {
            Set<Integer> keySet = userDataMap.keySet();
            //ArrayList<User> listOfUsers = new ArrayList<User>(keySet);
        }
        return requiredUsersList;
    }

    public String updateUser(User newUserData, Integer userId) {

        if(userDataMap.containsKey(userId)) {
            userDataMap.put(userId, newUserData);
            return "User data updated";
        } else {
            return "User not found";
        }
    }



    public String deleteUser(Integer userId) {

        if(userDataMap.containsKey(userId)) {
            userDataMap.remove(userId);
            return "User deleted with user Id" + userId;
        }
        else {
            return "User not found";
        }
    }

    static public String testStatic(Integer num) {
        userList.add(num);
        return "hello";
    }
}
