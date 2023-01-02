package com.ideas.org.crud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_ide")
    private Integer ide;

    @Column(name = "use_nam", nullable = false, unique = true)
    private String username;

    @Column(name = "use_pass", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "use_ide", referencedColumnName = "use_ide"),
            inverseJoinColumns = @JoinColumn(name = "rol_ide", referencedColumnName = "rol_ide")
    )
    private Set<Rol> roles;

    public void addRol(Rol rol) {

        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(rol);
    }
}
