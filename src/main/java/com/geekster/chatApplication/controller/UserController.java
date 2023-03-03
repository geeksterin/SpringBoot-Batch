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

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody String requestData) {
        JSONObject requestJson = new JSONObject(requestData);

        JSONObject isValidLogin = validateLogin(requestJson);

        if (isValidLogin.isEmpty()) {
            String username = requestJson.getString("username");
            String password = requestJson.getString("password");
            JSONObject responseObj = userService.login(username, password);
            if(responseObj.has("errorMessage")) {
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>(isValidLogin.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private JSONObject validateLogin(JSONObject requestJson) {

        JSONObject errorList = new JSONObject();

        if(!requestJson.has("username")) {
            errorList.put("username", "missing parameter");
        }
        if(!requestJson.has("password")) {
            errorList.put("password", "missing parameter");
        }
        return errorList;
    }

    @PutMapping(value = "/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody String requestData) {
        JSONObject isRequestValid = validateUserRequest(requestData);
        Users user = null;

        if(isRequestValid.isEmpty()) {
            user = setUser(requestData);
            JSONObject responseObj = userService.updateUser(user, userId);
            if(responseObj.has("errorMessage")) {
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(isRequestValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("user updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){

        userService.deleteUserByUserId(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    private JSONObject validateUserRequest(String userData) {
        JSONObject userJson = new JSONObject(userData);
        JSONObject errorList = new JSONObject();


        if(userJson.has("username")) {
            String username = userJson.getString("username");
            if(!userJson.has("isUpdate")){
                List<Users> userList = userRepository.findByUsername(username);
                if(userList.size() > 0) {
                    errorList.put("username", "This username already exists");
                    return errorList;
                }
            }

        } else {
            errorList.put("username", "Missing parameter");
        }

        if(!userJson.has("isUpdate")) {
            if(userJson.has("password") ) {
                String password = userJson.getString("password");
                if(!CommonUtils.isValidPassword(password)) {
                    errorList.put("password", "Please enter valid password eg: Akshay@12390");
                }
            } else {
                errorList.put("password", "Missing parameter");
            }
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
        if(!json.has("isUpdate")) {
            user.setPassword(json.getString("password"));
        }

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
