package org.gribov.controller;

import org.gribov.model.Order;
import org.gribov.security.CustomUserDetailsService;
import org.gribov.service.HydrobiontService;
import org.gribov.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class MyOrderController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HydrobiontService hydrobiontService;

    /**
     * Get - метод создания заказа
     */
    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        orderService.createOrder(); //создаю заказ, добавляю в репозиторий
        List<Order> orderList = orderService.getAllOrder(); //получаю все заказы из репозитория
        customUserDetailsService.setNewUniqueBasketNumToUser(); //обновляю номер корзины
        model.addAttribute("order", orderList);
        return allOrderToCurrentUser(model);
    }

    /**
     * Get - метод показать все заказы всех пользователей (для администратора)
     */
    @GetMapping("/allOrder")
    public String allOrder(Model model) {
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("order", orderList);
        return "order";
    }

    /**
     * Метод закрыть заказ по id
     */
    @RequestMapping("/closeOrder")
    public String closeOrder(@RequestParam(value = "id") Long id, Model model) {
        orderService.setReturnedOrder(id);
        model.addAttribute("id", id);
        return allOrder(model);
    }


    /**
     * Get - метод показать все заказы для текущего пользователя
     */
    @GetMapping("/allOrderToCurrentUser")
    public String allOrderToCurrentUser(Model model) {
        List<Order> orderList = orderService.getOrderByCurrentUser();
        model.addAttribute("order", orderList);
        return "orderUserId";
    }

}
