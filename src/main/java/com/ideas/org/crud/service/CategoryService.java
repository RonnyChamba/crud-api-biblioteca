package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.CategoryDTO;
import com.ideas.org.crud.dto.CategoryBookResponse;
import com.ideas.org.crud.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryDTO categoryDTO);

    List<CategoryResponse> findAll();

    CategoryDTO findByIde(Integer ide);

    List<CategoryBookResponse> findFetchBooksAll();

    void delete(Integer ide);

    CategoryResponse update(CategoryDTO categoryDTO, Integer ide);
}
