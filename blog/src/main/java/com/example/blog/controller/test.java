package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class test {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable("id") User user){
        try{
            userRepository.delete(user);
        }catch (EmptyResultDataAccessException e){
            return "삭제 실패 해당 id는 db에 없습니다.";
        }
        return user.getUsername()+"이 삭제되었습니다.";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다."));
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        return user;
    }

    @GetMapping("/dummy/users")
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @GetMapping("/dummy/user")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new IllegalArgumentException("해당 유저는 없습니다."));
    }

    @PostMapping("/dummy/join")
    public String join(@ModelAttribute  User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
