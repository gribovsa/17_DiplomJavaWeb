package org.gribov.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Самый главный контроллер
 */
@Controller
public class MyController {

    /**
     * Get - метод отображает главную страницу
     */
    @GetMapping("/home")
    String home() {
        return "home";
    }
}