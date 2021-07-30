package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model,
                        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        // /WEB-INF/views/index.jsp
        model.addAttribute("boards",boardService.postList(pageable));
        return "index"; //viewResolver 작동!! 해당 페이지로 model의 정보를 들고 이동함.
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, Model model){
        model.addAttribute("board",boardService.viewDetails(id));
        return "board/updateForm";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Integer id, Model model){
        model.addAttribute("board", boardService.viewDetails(id));
        return "board/detail";
    }

    //User 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
