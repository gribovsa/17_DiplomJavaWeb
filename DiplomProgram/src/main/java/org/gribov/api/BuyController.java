package org.gribov.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
@Tag(name = "Покупка")
public class BuyController {
    //Инжекция зависимости
    @Autowired
    BuyService buyService;

    /**
     * Post метод создания покупки
     */
    @PostMapping
    @Operation(summary = "Create new buy", description = "Создаёт покупку")
    public ResponseEntity<Buy> setNewBuy(@RequestBody Long hydrobiontId) {
        Buy buy;
        buy = buyService.createBuy(hydrobiontId);
        return new ResponseEntity<>(buy, HttpStatus.CREATED);
    }
}
