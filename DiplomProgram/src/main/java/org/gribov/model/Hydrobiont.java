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

    @Column(name = "name_generic")
    @Schema(name = "Родовое название")
    private String nameGeneric;

    @Column(name = "name_species_name")
    @Schema(name = "Видовое название")
    private String nameSpecies;

    @Column(name = "quantity")
    @Schema(name = "Количество")
    private Integer quantity;

    @Column(name = "price")
    @Schema(name = "Цена")
    private Float price;

    @Column(name = "photo")
    @Schema(name = "Фото")
    private String photo;

    @Column(name = "level")
    @Schema(name = "Уровень сложности")
    private String level;

    @Column(name = "rating")
    @Schema(name = "Рейтинг")
    private Long rating;


    //Для работы с сущностью обязателен конструктор без аргументов
    public Hydrobiont() {
    }

    public Hydrobiont(String direction, String type, String nameGeneric, String nameSpecies, Integer quantity,
                      Float price, String photo, String level, Long rating) {
        this.id = sequence++;
        this.direction = direction;
        this.type = type;
        this.nameGeneric = nameGeneric;
        this.nameSpecies = nameSpecies;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
        this.level = level;
        this.rating = rating;
    }


    public Hydrobiont(Long id, String direction, String type, String nameGeneric, String nameSpecies, Integer quantity,
                      Float price, String photo, String level, Long rating) {
        this.id = id;
        this.direction = direction;
        this.type = type;
        this.nameGeneric = nameGeneric;
        this.nameSpecies = nameSpecies;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
        this.level = level;
        this.rating = rating;
    }

    public void setNewRating(Long oldRating) {
        this.rating = oldRating + 1L;
    }

    public void setNewQuantity(Integer oldQuantity){
        if (oldQuantity>0){
            this.quantity = oldQuantity-1;
        }

    }
}


