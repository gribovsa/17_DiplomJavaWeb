package org.gribov.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.model.Order;
import org.gribov.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "Заказ")
public class OrderController {

    //Инжекция зависимости
    @Autowired
    private OrderService orderService;


    /**
     * Post метод создания заказа.
     */
    @PostMapping
    @Operation(summary = "Create new order", description = "Создаёт заказ")
    public ResponseEntity<Order> setNewOrder(@RequestBody OrderRequest request) {
        log.info("Получен запрос на создание заказа: userId = {}, basketNum = {}", request.getUserId(), request.getBasketNum());

        Order order;
        try {
            order = orderService.createOrder(request);
            log.info("201 -запрос выполнен успешно и привёл к созданию ресурса {}", order.toString());
            // После успешного создания заказа генерируем новый номер корзины
            //Buy.setIncrementBasketNum();
            return new ResponseEntity<>(order, HttpStatus.CREATED);
            // Обработка возможных ошибок
        } catch (NoSuchElementException e) {
            log.info("404 -  сервер не может найти данные согласно запросу");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.info("409 -  запрос не может быть выполнен из-за конфликтного обращения к ресурсу");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }


    /**
     * Get метод получения списка всех заказов
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> orders = orderService.getAllOrder();
        log.info(!orders.isEmpty() ? orders.toString() : "Список заказов пуст");
        return !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    /**
     * Get метод получения заказа по id
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get order by id", description = "Загружает заказ с указанным идентификатором")
    //---------------------------
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Искомый заказ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content)})
    //---------------------------
    public ResponseEntity<Order> getInfoOrderById(@PathVariable Long id) {
        final Order order;
        order = orderService.getInfoById(id);
        return order != null
                ? ResponseEntity.ok(order)
                : ResponseEntity.notFound().build();
    }


    /**
     * Put метод изменения заказа - метод закрытия заказа, отметкой времени закрытия
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update order by id, add return time", description = "Вносит изменение в заказ - ставит отметку о закрытии заказа")
    //--------------------------
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ изменён отметкой о закрытии",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "409", description = "Неверный ID заказа, заказ не найден",
                    content = @Content)})
    //--------------------------
    public ResponseEntity<Order> returnedOrder(@PathVariable Long id) {
        Order reurnedOrder = orderService.setReturnedOrder(id);
        return reurnedOrder != null
                ? ResponseEntity.ok(reurnedOrder)
                : ResponseEntity.badRequest().build();
    }
}
