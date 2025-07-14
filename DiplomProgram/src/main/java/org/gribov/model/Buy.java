package org.gribov.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Класс - покупка, характеризует покупку пользователя
 * покупки группируются по basket_num
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "buy")
@Schema(name = "Покупка")
public class Buy {
    public static long sequence = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(name = "hydrobiont_id")
    @Schema(name = "ID гидробионта")
    private Long hydrobiontId;


    @Getter
    @Column(name = "basket_num")
    @Schema(name = "Номер корзины")
    private Long basketNum;

    public Buy(Long hydrobiontId, Long basketNum) {
        this.id = sequence++;
        this.hydrobiontId = hydrobiontId;
        this.basketNum = basketNum;
    }

    public Buy(Long id, Long hydrobiontId, Long basketNum) {
        this.id = id;
        this.hydrobiontId = hydrobiontId;
        this.basketNum = basketNum;
    }
}
