package org.gribov.controller;

import org.gribov.api.BuyController;
import org.gribov.model.Buy;
import org.gribov.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MyBuyController {
    @Autowired
    private BuyService buyService;

//    @GetMapping("/buys/{basket_num})
//    public String listBuy(Model model, @PathVariable Long basket_num) {
//        List<Buy> buyList = buyService.getBuyByBasketNum(basketNum);
//        model.addAttribute("buys", buyList);
//        return "buys";
//    }

}
