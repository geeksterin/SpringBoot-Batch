package com.geekster.chatApplication.dao;

import com.geekster.chatApplication.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Integer> {
}
