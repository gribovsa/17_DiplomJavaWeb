package org.gribov.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(name = "Название роли")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
