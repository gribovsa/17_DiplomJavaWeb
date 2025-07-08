package org.gribov.controller;

import org.gribov.dto.UserDto;
import org.gribov.model.Hydrobiont;
import org.gribov.service.HydrobiontService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyHydrobiontController {

    HydrobiontService hydrobiontService;


    @GetMapping("/myHydrodiont")
    public String getListHydrobiont(Model model) {
        List<Hydrobiont> hydrobiontList =  hydrobiontService.getAllHydrobiont();
        model.addAttribute("hydrobionts", hydrobiontList);
        return "hydrobionts";
    }
}
