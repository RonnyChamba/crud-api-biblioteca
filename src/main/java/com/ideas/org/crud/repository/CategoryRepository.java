package com.ideas.org.crud.repository;

import com.ideas.org.crud.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


    boolean existsByNameIgnoreCase(String name);
    @Query("select distinct ca from Category  ca left join  fetch ca.books")
    List<Category> findFetchBook();
}
