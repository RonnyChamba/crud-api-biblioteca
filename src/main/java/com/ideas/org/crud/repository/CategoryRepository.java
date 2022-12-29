package com.ideas.org.crud.repository;

import com.ideas.org.crud.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


    boolean existsByNameIgnoreCase(String name);
}
