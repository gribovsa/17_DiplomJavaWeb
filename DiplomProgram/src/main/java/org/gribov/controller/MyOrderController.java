package org.gribov.controller;

import org.gribov.model.Order;
import org.gribov.security.CustomUserDetailsService;
import org.gribov.service.HydrobiontService;
import org.gribov.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
     * Метод создания заказа
     *
     * @param model
     * @return
     */
    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        orderService.createOrder(); //создаю заказ, добавляю в репозиторий
        List<Order> orderList = orderService.getAllOrder(); //получаю все заказы из репозитория
        customUserDetailsService.setNewUniqueBasketNumToUser(); //обновляю номер корзины
        model.addAttribute("order", orderList);
        return "order";
    }


    @GetMapping("/allOrder")
    public String allOrder(Model model) {
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("order", orderList);
        return "order";
    }
}
