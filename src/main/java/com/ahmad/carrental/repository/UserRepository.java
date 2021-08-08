package com.ahmad.carrental.repository;

import com.ahmad.carrental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
