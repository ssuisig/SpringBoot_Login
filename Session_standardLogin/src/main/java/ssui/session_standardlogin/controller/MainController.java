package ssui.session_standardlogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ssui.session_standardlogin.dto.UserDTO;
import ssui.session_standardlogin.service.UserService;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {
    final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String mainP(Model model) {

        UserDTO userDTO = new UserDTO();
        userDTO = userService.getAuthenticatedUserInfo();
        model.addAttribute("id", userDTO.getId());
        model.addAttribute("role", userDTO.getRole());


        return "main";
    }
}
