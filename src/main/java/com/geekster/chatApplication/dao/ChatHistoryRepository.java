package com.geekster.chatApplication.dao;

import com.geekster.chatApplication.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Integer> {

    @Query(value = "Select * from tbl_chat_history where sender_id = :senderId and status_id = 1",
            nativeQuery = true)
    List<ChatHistory> getChatsByUserId(int senderId);


    @Query(value = "select * from tbl_chat_history where (sender_id = :user1 and receiver_id = :user2)" +
            "or (sender_id = :user2 and receiver_id = :user1) and status_id  = 1 order by created_date",
            nativeQuery = true)
    List<ChatHistory> getConversation(int user1, int user2);
}
