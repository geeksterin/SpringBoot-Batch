package com.geekster.chatApplication.service;

import com.geekster.chatApplication.dao.ChatHistoryRepository;
import com.geekster.chatApplication.model.ChatHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoryService {

    @Autowired
    ChatHistoryRepository chatHistoryRepository;


    public int saveMessage(ChatHistory chat) {
        ChatHistory chatHistory = chatHistoryRepository.save(chat);
        return chatHistory.getChatId();
    }
}
