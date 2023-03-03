package com.geekster.chatApplication.controller;

import com.geekster.chatApplication.dao.StatusRepository;
import com.geekster.chatApplication.dao.UserRepository;
import com.geekster.chatApplication.model.ChatHistory;
import com.geekster.chatApplication.model.Status;
import com.geekster.chatApplication.model.Users;
import com.geekster.chatApplication.service.ChatHistoryService;
import com.geekster.chatApplication.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/chat")
public class ChatHistoryController {

    @Autowired
    ChatHistoryService chatHistoryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    @PostMapping(value = "/send-message")
    public ResponseEntity<String> saveMessage(@RequestBody String requestData) {
        JSONObject requestObj = new JSONObject(requestData);
        JSONObject errorList = validateRequest(requestObj);
        if(errorList.isEmpty()) {
            ChatHistory chat = setChatHistory(requestObj);
            int chatId = chatHistoryService.saveMessage(chat);
            return new ResponseEntity<>("message sent- " + chatId, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<String>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private ChatHistory setChatHistory(JSONObject requestObj) {
        ChatHistory chat = new ChatHistory();

        String message = requestObj.getString("message");
        int senderId =  requestObj.getInt("sender");
        int receiverId =  requestObj.getInt("receiver");
        Users senderUserObj = userRepository.findById(senderId).get();
        Users receiverUserObj = userRepository.findById(receiverId).get();

        chat.setReceiver(receiverUserObj);
        chat.setSender(senderUserObj);
        chat.setMessage(message);
        Status status = statusRepository.findById(1).get();
        chat.setStatusId(status);

        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        chat.setCreatedDate(createdTime);

        return chat;

    }

    private JSONObject validateRequest(JSONObject requestObj) {
        JSONObject errorObj = new JSONObject();
        if(!requestObj.has("sender")) {
            errorObj.put("sender", "Missing parameter");
        }
        if(!requestObj.has("receiver")) {
            errorObj.put("receiver", "Missing parameter");
        }
        if(requestObj.has("message")) {
            String message = requestObj.getString("message");
            if(message.isBlank() || message.isEmpty()) {
                errorObj.put("message", "message body can't be empty");
            }
        } else {
            errorObj.put("message", "Missing parameter");
        }
        return errorObj;
    }


}
