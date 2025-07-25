package org.gribov.controller;


import org.gribov.dto.UserDto;
import org.gribov.model.User;
import org.gribov.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyUserController {

    private UserService userService;

    public MyUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Старт <a href="http://localhost:8080">...</a>
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Get - метод отображения формы аутентификация
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Get - метод отображение формы регистрации
     */
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Post - метод регистрации пользователя
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUserDto(user);
        return "redirect:/register?success";
    }

    /**
     * Get - метод отображает всех зарегистрированных пользователей
     */
    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Get - метод деаутентификация
     */
    @GetMapping("/logout")
    public String logout() {
        //переход на данный URL обрабатывает метод filterChain класса SecurityConfig
        //данный метод открывает форму login, чтобы пользователь вновь аутентифицировался
        return "login";
    }

}