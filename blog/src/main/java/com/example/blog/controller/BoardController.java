package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){ //컨트롤러에서 세션을 어떻게 찾지?
        // /WEB-INF/views/index.jsp
        System.out.println("로그인 사용자 정보"+principal.getUsername());
        return "index";
    }

}
