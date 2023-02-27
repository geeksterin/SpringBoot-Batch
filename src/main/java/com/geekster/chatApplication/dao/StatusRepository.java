package com.geekster.chatApplication.dao;

import com.geekster.chatApplication.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
