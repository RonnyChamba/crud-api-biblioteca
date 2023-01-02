package com.ideas.org.crud.repository;

import com.ideas.org.crud.entities.Rol;
import com.ideas.org.crud.enums.RolEnun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {


    Optional<Rol> findByRolEnun(RolEnun rolEnun);
}
