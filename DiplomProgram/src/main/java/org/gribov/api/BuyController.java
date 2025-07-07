package org.gribov.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.model.Order;
import org.gribov.service.BuyService;
import org.gribov.service.OrderService;
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
    public ResponseEntity<Buy> setNewBuy(@RequestBody Long hydrobiontId) {
        Buy buy;
        buy = buyService.createBuy(hydrobiontId);
        return new ResponseEntity<>(buy, HttpStatus.CREATED);
    }

    /**
     * Метод генерации нового номера корзины
     */
    @PutMapping("/new")
    @Operation(summary = "Update basket number", description = "Вносит изменение в номер корзины, по сути это инкремент")
    public ResponseEntity<Long> updateBasketNum() {
        Long returnedBasketNum = buyService.setIncrementBasketNum();
        return returnedBasketNum != null
                ? ResponseEntity.ok(returnedBasketNum)
                : ResponseEntity.badRequest().build();
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
