package com.geekster.todo.controller;

import com.geekster.todo.model.User;
import com.geekster.todo.service.UserService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {

        String status = service.saveUser(user);
        return status;
    }


    @GetMapping("/user")
    public String getUser(@Nullable @RequestParam String userId) {

        return service.getUser(Integer.valueOf(userId)).toString();

    }

    @PutMapping("/user/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable String userId) {

        return service.updateUser(user, Integer.valueOf(userId));
    }


    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable Integer userId) {

        return service.deleteUser(userId);

    }



}
