package com.geekster.chatApplication.dao;

import com.geekster.chatApplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
