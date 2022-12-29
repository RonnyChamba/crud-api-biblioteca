package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.CategoryDTO;
import com.ideas.org.crud.dto.CategoryResponse;
import com.ideas.org.crud.entities.Category;
import com.ideas.org.crud.exeption.DuplicatedResourceException;
import com.ideas.org.crud.exeption.NotDataAccessException;
import com.ideas.org.crud.exeption.NotFoundException;
import com.ideas.org.crud.mapper.Mapper;
import com.ideas.org.crud.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);
    private CategoryRepository categoryRepository;

    private Mapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Mapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    @Transactional
    public CategoryResponse save(CategoryDTO categoryDTO) {


        try {

            if (categoryRepository.existsByNameIgnoreCase(categoryDTO.getName())) {
                throw new DuplicatedResourceException(String.format("Categoria %s ya existe", categoryDTO.getName()));
            }

            Category category = categoryMapper.mapperToEntity(categoryDTO);

            return categoryMapper.mapperToDTO(categoryRepository.save(category));

        } catch (DataAccessException e) {

            LOGGER.error("Error al guardar categoria", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {

        try {
            List<Category> categories = categoryRepository.findAll();

            if (categories.size() > 0) {
                return categories.stream()
                        .map((item) -> categoryMapper.mapperToDTO(item))
                        .collect(Collectors.toList());

            }

            return Collections.emptyList();
        } catch (DataAccessException e) {

            LOGGER.error("Error al listar categorias", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional
    public void delete(Integer ide) {

        try {

            if (categoryRepository.existsById(ide)) {
                categoryRepository.deleteById(ide);
                return;
            }
            throw new NotFoundException(String.format("Category %s no found", ide));

        } catch (DataAccessException e) {

            LOGGER.error("Error al eliminar categoria", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional
    public CategoryResponse update(CategoryDTO categoryDTO, Integer ide) {

        try {
            Category category = categoryRepository.findById(ide).orElseThrow(
                    () -> new NotFoundException(String.format("Categoria %s no existe", ide))
            );

            // Compare if new name ya exist
            if (categoryRepository.existsByNameIgnoreCase(categoryDTO.getName())) {
                throw new DuplicatedResourceException(String.format("Categoria con nombre %s  ya existe", categoryDTO.getName()));
            } else category.setName(categoryDTO.getName());


            if (categoryDTO.getDescription() != null) {
                category.setDescription(categoryDTO.getDescription());
            }

            return categoryMapper.mapperToDTO(categoryRepository.save(category));

        } catch (DataAccessException e) {

            LOGGER.error("Error al actualizar categoria", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }

    }
}
