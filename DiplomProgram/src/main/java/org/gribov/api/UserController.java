package org.gribov.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.gribov.dto.UserDto;
import org.gribov.model.Order;
import org.gribov.model.User;
import org.gribov.service.OrderService;
import org.gribov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "Пользователи")
public class UserController {

    //Инжекция зависимостей
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;


    //ручки
    @GetMapping
    @Operation(summary = "Get all users", description = "Загружает список пользователей")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> users = userService.findAllUsers();
        log.info(!users.isEmpty() ? users.toString() : "none");
        return !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Загружает пользователя с указанным идентификатором")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findUserById(id);
        log.info(user.toString());
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/order")
    @Operation(summary = "Get all orders by reader id", description = "Загружает список заказов, для пользователя с указанным идентификатором")
    public ResponseEntity<List<Order>> getOrderByIdReader(@PathVariable Long id) {
        List<Order> userOrders;
        userOrders = orderService.getOrderByIdUser(id);
        return !userOrders.isEmpty()
                ? new ResponseEntity<>(userOrders, HttpStatus.OK)
                : ResponseEntity.notFound().build();

    }


    @PostMapping
    @Operation(summary = "Create new user", description = "Создаёт и сохраняет нового пользователя")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User newUser = userService.saveUserDto(userDto);//addUser(user);
        return newUser != null
                ? new ResponseEntity<>(newUser, HttpStatus.CREATED)
                : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Удаляет пользователя с указанным идентификатором")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
