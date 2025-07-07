package org.gribov;

import lombok.extern.slf4j.Slf4j;
import org.gribov.dto.UserDto;
import org.gribov.model.Hydrobiont;
import org.gribov.model.Order;
import org.gribov.model.Role;
import org.gribov.repository.HydrobiontRepository;
import org.gribov.repository.OrderRepository;
import org.gribov.repository.RoleRepository;
import org.gribov.repository.UserRepository;
import org.gribov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@SpringBootApplication
public class DiplomProgramApplication {
    @Autowired
    private HydrobiontRepository hydrobiontRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DiplomProgramApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generate() {
        log.info("Run method filling repositories: generate()");

        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Криптокорина", "Спиралис", 10, 115.20F, "1.png","Простое", null ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Криптокорина", "Фламинго", 16, 250.50F, "2.png","Сложное", null ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Эхинодорус", "Сатурн Игуасу 2009", 3, 1500.00F, "3.png","Среднее", null ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Эхинодорус", "Доброта песочного человека", 2, 450.00F, "4.png","Простое", null ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Утрикулярия", "Грамнифолия", 10, 500.00F, "5.png", "Сложное",null ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Ломариопсис", "Линеата", 10, 100.00F, "6.png","Очень простое", null ));

//        userService.saveUserDto(new UserDto(null, "Алексей", "Иванов", "ivanov@mail.ru", "1234"));
//        userService.saveUserDto(new UserDto(null, "Иван", "Петров", "petrov@mail.ru", "1234"));
//        userService.saveUserDto(new UserDto(null, "Дмитрий", "Андреев", "andreev@mail.ru", "1234"));
//
//        roleRepository.save(new Role(null, "ROLE_ADMIN"));

//        orderRepository.save(new Order(null, 1L, 1L, null, LocalDateTime.now(), null));
//        orderRepository.save(new Order(null, 2L, 1L, null, LocalDateTime.now(), null));
//
//        orderRepository.save(new Order(null, 3L, 2L, null, LocalDateTime.now(), null));
//        orderRepository.save(new Order(null, 4L, 2L, null, LocalDateTime.now(), null));
//
//        orderRepository.save(new Order(null, 5L, 3L, null, LocalDateTime.now(), null));
//        orderRepository.save(new Order(null, 6L, 3L, null, LocalDateTime.now(), null));

    }
}
