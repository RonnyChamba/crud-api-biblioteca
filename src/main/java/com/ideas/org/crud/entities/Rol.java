package com.ideas.org.crud.entities;

import com.ideas.org.crud.enums.RolEnun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_ide")
    private  Integer ide;

    @Column(name = "rol_tip")
    @Enumerated(EnumType.STRING)
    private RolEnun rolEnun;


}
