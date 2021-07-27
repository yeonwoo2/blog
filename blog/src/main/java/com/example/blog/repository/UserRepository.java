package com.example.blog.repository;

import com.example.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //로그인 JPA Naming 쿼리
    User findByUsernameAndPassword(String username, String password);
}
