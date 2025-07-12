package org.gribov;

import lombok.extern.slf4j.Slf4j;
import org.gribov.model.Hydrobiont;
import org.gribov.repository.HydrobiontRepository;
import org.gribov.repository.OrderRepository;
import org.gribov.repository.RoleRepository;
import org.gribov.repository.UserRepository;
import org.gribov.service.BuyService;
import org.gribov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
    @Autowired
    BuyService buyService;

    public static void main(String[] args) {
        SpringApplication.run(DiplomProgramApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void generate() {
        log.info("Run method filling repositories: generate()");

        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Криптокорина", "Спиралис", 10, 115.20F, "images/spiralis.jpg","Простое", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Криптокорина", "Фламинго", 16, 250.50F, "images/flamingo.jpg","Сложное", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Эхинодорус", "Сатурн Игуасу 2009", 3, 1500.00F, "images/saturn.jpg","Среднее", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Эхинодорус", "Доброта песочного человека", 2, 450.00F, "images/sandman.jpg","Простое", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Эхинодорус", "Красный жемчуг", 1, 600.00F, "images/red.jpg","Простое", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Утрикулярия", "Грамнифолия", 10, 500.00F, "images/utricularia.jpg", "Сложное",0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Ломариопсис", "Линеата", 10, 100.00F, "images/lomariopsis.jpg","Очень простое", 0L ));
        hydrobiontRepository.save(new Hydrobiont(null, "Пресное", "Растение", "Анубиас", "Golden", 10, 200.00F, "images/gold.jpg","Очень простое", 0L ));

    }
}
