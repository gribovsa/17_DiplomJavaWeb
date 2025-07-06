package org.gribov.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс - корзина покупок, в котором находятся покупки пользователей
 * сгруппированные по basket_num
 */
@Data
@Entity
@Table(name = "basket")
@Schema(name = "Корзина заказов")
public class Basket {
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

    @Column(name = "basket_num")
    @Schema(name = "Номер корзины")
    private Long basketNum;

    public Basket(Long hydrobiontId, Long userId) {
        this.id = sequence++;
        this.hydrobiontId = hydrobiontId;
        this.userId = userId;
    }

    /**
     * Метод генерации номера подкорзины
     */
    public void setBasketNum() {
        this.basketNum = sequence++;
    }
}
