package com.geekster.instagram.service;

import com.geekster.instagram.dao.UserRepository;
import com.geekster.instagram.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int saveUser(User user) {
        User userObj = userRepository.save(user);
        return userObj.getUserId();
    }
}
