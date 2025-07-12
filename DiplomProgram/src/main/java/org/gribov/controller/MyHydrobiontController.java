package org.gribov.controller;


import org.gribov.model.Hydrobiont;
import org.gribov.service.HydrobiontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyHydrobiontController {

    @Autowired
    HydrobiontService hydrobiontService;


    /**
     * Get - метод показать все товары - гидробионты
     */
    @GetMapping("/myHydrobiont")
    public String getListHydrobiont(Model model) {
        List<Hydrobiont> hydrobiontList =  hydrobiontService.getAllHydrobiont();
        model.addAttribute("hydrobiont", hydrobiontList);
        return "hydrobionts";
    }
}
