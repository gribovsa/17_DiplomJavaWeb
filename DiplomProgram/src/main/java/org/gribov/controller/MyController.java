package org.gribov.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class MyController {




    @GetMapping("/home")
    String home() {
        return "home";
    }

}