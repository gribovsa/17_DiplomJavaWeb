package org.gribov.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * Класс - заказ гидробионтов
 */
@Data
@Entity
@Table(name = "order")
@Schema(name = "Заказ")
public class Order {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(name = "hydrobiont_id")
    @Schema(name = "ID гидробионта")
    private Long hydrobiontId;

    @Column(name = "user_id")
    @Schema(name = "ID пользователя")
    private Long userId;

    @Column(name = "total_price")
    @Schema(name = "Итоговая цена")
    private Float totalPrice;

    @Schema(name = "Дата создания")
    private LocalDateTime timestamp;

    @Schema(name = "Дата закрытия")
    private LocalDateTime returnedTimestamp;

    //Для работы с сущностью обязателен конструктор без аргументов
    public Order() {
    }

    public Order(Long hydrobiontId, Long userId, LocalDateTime timestamp) {
        this.id = sequence++;
        this.hydrobiontId = hydrobiontId;
        this.userId = userId;
        this.timestamp = timestamp;
    }


    public Order(Long id, Long hydrobiontId, Long userId, Float totalPrice, LocalDateTime timestamp, LocalDateTime returnedTimestamp) {
        this.id = id;
        this.hydrobiontId = hydrobiontId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
        this.returnedTimestamp = returnedTimestamp;
    }
}
