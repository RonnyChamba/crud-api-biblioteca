package com.ideas.org.crud.controller;

import com.ideas.org.crud.dto.CategoryBookResponse;
import com.ideas.org.crud.dto.CategoryDTO;
import com.ideas.org.crud.dto.CategoryResponse;
import com.ideas.org.crud.service.CategoryService;
import com.ideas.org.crud.util.UtilFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = UtilFunction.CROSS_ORIGIN)
public class CategoryController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryDTO categoryDTO) {

        LOGGER.debug(String.format("Category to Persist %s", categoryDTO));
        CategoryResponse response = categoryService.save(categoryDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<String, Object>> findAll() {

        List<CategoryResponse> categories = categoryService.findAll();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", categories);
        mapData.put("size", categories.size());

        return ResponseEntity.ok(mapData);
    }
    @GetMapping("/categories/{ide}")
    public ResponseEntity<CategoryDTO> findByIde(@PathVariable Integer ide) {


        CategoryDTO categoryDTO = categoryService.findByIde(ide);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/categories/books")
    public ResponseEntity<Map<String, Object>> findFetchBookAll() {

        List<CategoryBookResponse> categories = categoryService.findFetchBooksAll();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", categories);
        mapData.put("size", categories.size());
        return ResponseEntity.ok(mapData);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/categories/{ide}")
    public ResponseEntity<?> delete(@PathVariable Integer ide) {

        LOGGER.debug(String.format("Category to delete %s", ide));
        categoryService.delete(ide);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/categories/{ide}")
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryDTO categoryDTO,
                                                   @PathVariable Integer ide) {

        CategoryResponse response = categoryService.update(categoryDTO, ide);

        return  ResponseEntity.ok(response);
    }
}
