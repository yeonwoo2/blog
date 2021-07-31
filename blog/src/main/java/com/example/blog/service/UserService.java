package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void join(User user){
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        //수정시에는 영속성 컨텍스트에 User 오브젝트를 영속화시키고 영속화된 User 오브젝트를 수정
        //select를 해서 User오브젝트를 db로 부터 가져오는 이유는 영속화를 하기위해서.
        //영속화된 오브젝트를 변경하면 자동으로 db에 update문을 날려준다.
        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("회원찾기 실패"));

        if(persistence.getOauth() == null || persistence.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistence.setPassword(encPassword);
        }
            persistence.setEmail(user.getEmail());
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userRepository.findByUsername(username).orElseGet(()->new User());
    }
}















