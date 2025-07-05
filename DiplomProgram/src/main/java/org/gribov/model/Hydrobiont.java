package org.gribov.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hydrobiont")
@Schema(name = "Гидробионт")
public class Hydrobiont {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(name = "direction")
    @Schema(name = "Направление")
    private String direction;

    @Column(name = "type")
    @Schema(name = "Тип")
    private String type;

    @Column(name = "generic_name")
    @Schema(name = "Родовое название")
    private String genericName;

    @Column(name = "species_name")
    @Schema(name = "Видовое название")
    private String speciesName;

    @Column(name = "quantity")
    @Schema(name = "Количество")
    private String quantity;

    @Column(name = "price")
    @Schema(name = "Цена")
    private Float price;


    //Для работы с сущностью обязателен конструктор без аргументов
    public Hydrobiont() {
    }

    public Hydrobiont(String genericName, String speciesName) {
        this.id = sequence++;
        this.genericName = genericName;
        this.speciesName = speciesName;
    }


    public Hydrobiont(Long id, String direction, String type, String genericName, String speciesName, String quantity, Float price) {
        this.id = id;
        this.direction = direction;
        this.type = type;
        this.genericName = genericName;
        this.speciesName = speciesName;
        this.quantity = quantity;
        this.price = price;
    }
}


