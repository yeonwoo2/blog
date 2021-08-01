package com.example.blog.service;

import com.example.blog.entity.Board;
import com.example.blog.entity.Reply;
import com.example.blog.entity.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.ReplyRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void write(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> postList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board viewDetails(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 상세보기 실패"));
    }

    @Transactional
    public void postDelete(Integer id) {
         boardRepository.deleteById(id);
    }

    @Transactional
    public void postUpdate(Integer id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 찾기 실패"));
        board.setTitle(requestBoard.getTitle());
        board.setTitle(requestBoard.getContent());
        //해당 함수 종료시 트랜잭션 종료 이때 더티체킹 그리고 자동 업데이트 flush
    }

    @Transactional
    public void replyWrite(User user, Integer boardId, Reply requestReply) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("댓글 쓰기 실패"));

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);

    }
}
