package com.geekster.chatApplication.controller;

import com.geekster.chatApplication.Util.CommonUtils;
import com.geekster.chatApplication.dao.StatusRepository;
import com.geekster.chatApplication.dao.UserRepository;
import com.geekster.chatApplication.model.Status;
import com.geekster.chatApplication.model.Users;
import com.geekster.chatApplication.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {


    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping(value = "/create-user")
    public ResponseEntity<String> createUser(@RequestBody String userData) {

        //Validation of user data by converting to json and checking keys
        JSONObject isRequestValid = validateUserRequest(userData);

        Users user = null;

        if(isRequestValid.isEmpty()) {
            user = setUser(userData);
            userService.saveUser(user);
        } else {
            return new ResponseEntity<String>(isRequestValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }


    @GetMapping(value = "/get-users")
    public ResponseEntity<String> getUsers(@Nullable @RequestParam String userId) {

        JSONArray userArr = userService.getUsers(userId);
        return new ResponseEntity<>(userArr.toString(), HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){

        userService.deleteUserByUserId(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }





    private JSONObject validateUserRequest(String userData) {
        JSONObject userJson = new JSONObject(userData);
        JSONObject errorList = new JSONObject();

        if(userJson.has("username")) {
            String username = userJson.getString("username");
            List<Users> userList = userRepository.findByUsername(username);
            if(userList.size() > 0) {
                errorList.put("username", "This username already exists");
                return errorList;
            }
        } else {
            errorList.put("username", "Missing parameter");
        }

        if(userJson.has("password")) {
            String password = userJson.getString("password");
            if(!CommonUtils.isValidPassword(password)) {
                errorList.put("password", "Please enter valid password eg: Akshay@12390");
            }
        } else {
            errorList.put("password", "Missing parameter");
        }

        if(userJson.has("firstName")) {
            String firstName = userJson.getString("firstName");
        } else {
            errorList.put("firstName", "Missing parameter");
        }

        if(userJson.has("email")) {
            String email = userJson.getString("email");
            if(!CommonUtils.isValidEmail(email)) {
                errorList.put("email", "Please enter a valid email");
            }
        } else {
            errorList.put("email", "Missing parameter");
        }

        if(userJson.has("phoneNumber")) {
            String phoneNumber = userJson.getString("phoneNumber");
            if(!CommonUtils.isValidPhoneNumber(phoneNumber)) {
                errorList.put("phoneNumber", "Please enter a valid phone number");
            }
        } else {
            errorList.put("phoneNumber", "Missing parameter");
        }

        return errorList;

    }

    private Users setUser(String userData)  {
        Users user = new Users();
        JSONObject json = new JSONObject(userData);

        user.setEmail(json.getString("email"));
        user.setPassword(json.getString("password"));
        user.setFirstName(json.getString("firstName"));
        user.setUsername(json.getString("username"));
        user.setPhoneNumber(json.getString("phoneNumber"));

        if(json.has("age")) {
            user.setAge(json.getInt("age"));
        }

        if(json.has("lastName")){
            user.setLastName(json.getString("lastName"));
        }
        if(json.has("gender")){
            user.setGender(json.getString("gender"));
        }
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(createdTime);

        Status status = statusRepository.findById(1).get();
        user.setStatusId(status);

        return user;

    }
}
