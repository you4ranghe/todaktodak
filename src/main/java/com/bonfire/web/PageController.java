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

    /** 루트: 로그인 없이 바로 소각장 연출 화면으로 */
    @GetMapping("/")
    public String home() {
        return "redirect:/preview";
    }

    /** 로그인 불필요 — 연출 중심 화면 (백엔드 미연동) */
    @GetMapping("/preview")
    public String preview() {
        return "preview";
    }

    /** 로그인 연동 본화면 (Phase 2 실 API 대비, 인증 필요) */
    @GetMapping("/app")
    public String incinerator(Model model) {
        model.addAttribute("categories", Category.values());
        return "incinerator";
    }
}
