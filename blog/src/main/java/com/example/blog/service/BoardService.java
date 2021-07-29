package com.example.blog.service;

import com.example.blog.entity.Board;
import com.example.blog.entity.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public List<Board> postList() {
        return boardRepository.findAll();
    }
}
