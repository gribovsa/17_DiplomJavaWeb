package org.gribov.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gribov.model.Buy;
import org.gribov.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void setNewBuy(@RequestBody Long hydrobiontId) {
        buyService.createBuy(hydrobiontId);
    }

    /**
     * Get - метод возвращает список записей (покупок) по номеру корзины basket_num
     */
    @GetMapping("/{basket_num}")
    @Operation(summary = "Get order by id", description = "Загружает список покупок по номеру корзины")
    public ResponseEntity<List<Buy>> getBuyByBasketNum(@PathVariable Long basket_num) {
        List<Buy> buyList;
        buyList = buyService.getBuyByBasketNum(basket_num);
        return !buyList.isEmpty()
                ? new ResponseEntity<>(buyList, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

}
