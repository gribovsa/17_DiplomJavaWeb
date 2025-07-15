package org.gribov.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(nullable = false, length = 4096)
    @Schema(name = "Имя")
    private String name;

    @Column(nullable = false, unique = true, length = 4096)
    @Schema(name = "Email")
    private String email;

    @Column(nullable = false)
    @Schema(name = "Пароль")
    private String password;

    @Column(name = "now_basket_num", nullable = false)
    @Schema(name = "Номер используемой козины")
    private Long nowBasketNum;

    //Объединение таблиц многие ко многим, создам таблицу соответствия ролей и пользователей users_roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles = new ArrayList<>();


}




