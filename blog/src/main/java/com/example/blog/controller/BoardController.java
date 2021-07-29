package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model){
        // /WEB-INF/views/index.jsp
        model.addAttribute("boards",boardService.postList());
        return "index"; //viewResolver 작동!! 해당 페이지로 model의 정보를 들고 이동함.
    }

    //User 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
