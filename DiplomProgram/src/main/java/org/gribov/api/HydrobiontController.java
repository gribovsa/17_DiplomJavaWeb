package org.gribov.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.service.BuyService;
import org.gribov.service.HydrobiontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hydrobiont")
@Tag(name = "Гидробионт")
public class HydrobiontController {

    //Инжекция зависимости
    @Autowired
    private HydrobiontService hydrobiontService;
    @Autowired
    private BuyService buyService;


    //ручки
    @GetMapping
    @Operation(summary = "Get all hydrobiont", description = "Загружает список всех гидробионтов")
    public ResponseEntity<List<Hydrobiont>> getAllBook() {
        List<Hydrobiont> hydrobiontList = hydrobiontService.getAllHydrobiont();
        log.info(!hydrobiontList.isEmpty() ? hydrobiontList.toString() : "none");
        return !hydrobiontList.isEmpty()
                ? new ResponseEntity<>(hydrobiontList, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get hydrobiont by id", description = "Загружает гидробионт с указанным идентификатором")
    public ResponseEntity<Hydrobiont> getHydrobiontById(@PathVariable long id) {
        Hydrobiont returnedHydrobiont = hydrobiontService.getHydrobiontById(id);
        log.info(returnedHydrobiont != null ? returnedHydrobiont.toString() : "none");
        return returnedHydrobiont != null
                ? new ResponseEntity<>(returnedHydrobiont, HttpStatus.OK)// или ResponseEntity.ok(book)
                : ResponseEntity.notFound().build();
    }


    @PostMapping
    @Operation(summary = "Create new hydrobiont", description = "Создаёт новый гидробионт")
    public ResponseEntity<Hydrobiont> createHydrobiont(@RequestBody Hydrobiont book) {
        Hydrobiont newHydrobiont = hydrobiontService.addHydrobiont(book);
        log.info(newHydrobiont != null ? newHydrobiont.toString() : "none");
        return newHydrobiont != null
                ? new ResponseEntity<>(newHydrobiont, HttpStatus.CREATED)
                : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hydrobiont by id", description = "Удаляет гидробионт с указанным идентификатором")
    public ResponseEntity<Void> deleteHydrobiont(@PathVariable Long id) {
        hydrobiontService.deleteHydrobiontById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Метод возвращает количество купленных гидробионтов по id гидробионта
     * это необходимо для статистики - будем заполнять поле рейтинг у гидробионта
     */
    @GetMapping("/{id}/buy/quantity")
    @Operation(summary = "Get order by id", description = "Загружает список покупок по номеру корзины")
    public ResponseEntity<Integer> getBuyByBasketNum(@PathVariable Long id) {
        Integer returnedQuantity = buyService.getQuantityBuyByHydrobiontId(id);
        return returnedQuantity != null
                ? new ResponseEntity<>(returnedQuantity, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

}
