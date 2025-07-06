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
@Table(name = "new_order")
@Schema(name = "Заказ")
public class Order {

    public static long sequence = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(name = "basket_num")
    @Schema(name = "Номер корзины товаров")
    private Long basketNum;

    @Column(name = "user_id")
    @Schema(name = "ID пользователя")
    private Long userId;

    @Column(name = "total_price")
    @Schema(name = "Итоговая цена")
    private Float totalPrice;

    @Column(name = "timestamp")
    @Schema(name = "Дата создания")
    private LocalDateTime timestamp;

    @Column(name = "returnedTime")
    @Schema(name = "Дата закрытия")
    private LocalDateTime returnedTimestamp;



    //Для работы с сущностью обязателен конструктор без аргументов
    public Order() {
    }

    public Order(Long basketNum, Long userId) {
        this.id = sequence++;
        this.basketNum = basketNum;
        this.userId = userId;
    }


    public Order(Long id, Long basketNum, Long userId, Float totalPrice, LocalDateTime timestamp, LocalDateTime returnedTimestamp) {
        this.id = id;
        this.basketNum = basketNum;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
        this.returnedTimestamp = returnedTimestamp;
    }

//Объединение таблиц, добавлю к каждому заказу пользователя
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "new_user_id")
//    private User user;
}
