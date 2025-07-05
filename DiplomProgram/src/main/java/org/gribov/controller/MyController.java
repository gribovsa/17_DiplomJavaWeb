package org.gribov.controller;

import org.gribov.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class MyController {


    private UserService userService;

    @GetMapping("/home")
    String home() {
        return "home";
    }

}