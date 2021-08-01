package com.example.blog.controller.api;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.dto.ReplySaveRequestDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Board;
import com.example.blog.entity.Reply;
import com.example.blog.entity.User;
import com.example.blog.service.BoardService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;



    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.write(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Integer id){
        boardService.postDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Integer id, @RequestBody Board board){
        boardService.postUpdate(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 조핟.
    //dto 사용하지 않은 이유는!!
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replyDto){
        boardService.replyWrite(replyDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
