package ssui.session_standardlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginP() {

        return "login";
    }

    @PostMapping("loginProc")
    public String loginProcess() {

        return "...";
    }
}
