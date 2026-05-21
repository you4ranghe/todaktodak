package com.bonfire.web;

import com.bonfire.domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // 루트("/")는 컨트롤러 매핑을 두지 않는다.
    // → Spring Boot가 정적 리소스 static/index.html 을 환영 페이지로 서빙(로그인 불필요).
    //   (동일 파일을 Vercel 정적 배포에서도 그대로 사용)

    /** 로그인 연동 본화면 (Phase 2 실 API 대비, 인증 필요) */
    @GetMapping("/app")
    public String incinerator(Model model) {
        model.addAttribute("categories", Category.values());
        return "incinerator";
    }
}
