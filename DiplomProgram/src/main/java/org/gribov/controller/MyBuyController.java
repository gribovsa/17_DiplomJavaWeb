package org.gribov.controller;


import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.security.CustomUserDetailsService;
import org.gribov.service.BuyService;
import org.gribov.service.HydrobiontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
public class MyBuyController {
    @Autowired
    private BuyService buyService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private HydrobiontService hydrobiontService;

    /**
     * Метод получения списка гидробионтов из текущей корзины
     * @param model
     * @return
     */
    @GetMapping("/basket")
    public String listBuy(Model model) {
        List<Buy> buyList = buyService.getBuyByBasketNum(customUserDetailsService.getNowBasketNum()); //получили список покупок в текущей корзине
        List<Hydrobiont> basketHydrobiontList = new ArrayList<>(); //сделаем новый список, в который положим гидробионтов из корзины по их id из списка покупок
        for (Buy buy : buyList) {
            basketHydrobiontList.add(hydrobiontService.getHydrobiontById(buy.getHydrobiontId()));
        }
        model.addAttribute("hydrobiont", basketHydrobiontList);
        return "basket";
    }






    /**
     * Метод совершения покупки корзину
     */
    @RequestMapping("/addBuy")
    public String addBuyToBasket(@RequestParam(value = "action") String action, Model model){

        model.addAttribute("actions", action);
        return "home";


    }


}
