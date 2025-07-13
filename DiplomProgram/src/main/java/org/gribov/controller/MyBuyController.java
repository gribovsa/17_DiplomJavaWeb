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


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MyBuyController {
    @Autowired
    private BuyService buyService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private HydrobiontService hydrobiontService;
    @Autowired
    private MyHydrobiontController myHydrobiontController;

    /**
     * Метод получения списка гидробионтов из текущей корзины
     */
    @GetMapping("/basket")
    public String listBuy(Model model) {
        float sumPrice = 0F;
        List<Buy> buyList = buyService.getBuyByBasketNum(customUserDetailsService.getNowBasketNum()); //получили список покупок в текущей корзине
        Map<Long, Hydrobiont> buyMap = new HashMap<>();//сделаем map, в котором ключ - номер покупки из репозитория, значение - сам товар (гидробионт)
        for (Buy buy : buyList) {
            Hydrobiont hydrobiont = hydrobiontService.getHydrobiontById(buy.getHydrobiontId());
            buyMap.put(buy.getId(), hydrobiont); //делаю map, где ключ - это номер покупки, а значение сам товар - гидробионт
            sumPrice = sumPrice + hydrobiont.getPrice(); //вычисляю суммарную цену покупок
        }
        model.addAttribute("buyMap", buyMap);
        model.addAttribute("sumPrice", sumPrice);
        return "basket";
    }


    /**
     * Метод совершения покупки корзину
     */
    @RequestMapping("/addBuy")
    public String addBuyToBasket(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("id", id);
        buyService.createBuy(id);
        System.out.println("Добавлен товар номер: " + id);
        return myHydrobiontController.getListHydrobiont(model);
    }

    /**
     * Метод получения списка гидробионтов из корзины по номеру корзины
     */
    @RequestMapping("/basketNum")
    public String listBuyToBasketNum(@RequestParam(value = "basketNum") Long basketNum, Model model) {
        float sumPrice = 0F;
        List<Buy> buyList = buyService.getBuyByBasketNum(basketNum); //получили список покупок в текущей корзине
        Map<Long, Hydrobiont> buyMap = new HashMap<>();//сделаем map, в котором ключ - номер покупки из репозитория, значение - сам товар (гидробионт)
        for (Buy buy : buyList) {
            Hydrobiont hydrobiont = hydrobiontService.getHydrobiontById(buy.getHydrobiontId());
            buyMap.put( buy.getId(), hydrobiont); //делаю map, где ключ - это номер покупки, а значение сам товар - гидробионт
            sumPrice = sumPrice + hydrobiont.getPrice(); //вычисляю суммарную цену покупок
        }
        model.addAttribute("buyMap", buyMap);
        model.addAttribute("sumPrice", sumPrice);
        return "basketNum";
    }

    /**
     * Метод удаления покупки из корзины
     */
    @RequestMapping("/deleteBuy")
    public String deleteBuyToBasket(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("id", id);
        buyService.deleteBuyById(id);
        return listBuy(model);
    }

    /**
     * Метод получения стоимость всех покупок из корзины
     */
    @RequestMapping("/basketSum")
    public String sumBuyToBasketNum(@RequestParam(value = "basketNum") Long basketNum, Model model) {
        Float sum = buyService.getTotalPrice(basketNum);
        model.addAttribute("sum", sum);
        return listBuyToBasketNum(basketNum, model);

    }
}
