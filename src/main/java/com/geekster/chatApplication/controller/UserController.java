package com.geekster.chatApplication.controller;

import com.geekster.chatApplication.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/user")
public class UserController {

//    @PostMapping(name = "/create-user")
//    public ResponseEntity<String> createUser(@RequestBody String userData) {
//        Users user = setUser(userData);
//
//
//    }
//
//    private Users setUser(String userData) {
//        Users user = new Users();
//
//    }
}
