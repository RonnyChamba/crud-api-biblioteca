package com.ideas.org.crud.controller;

import com.ideas.org.crud.dto.BookDTO;
import com.ideas.org.crud.dto.BookResponse;
import com.ideas.org.crud.service.BookService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookDTO bookDTO) {

        LOGGER.debug(String.format("Book to Persist %s", bookDTO));

        BookResponse response = bookService.save(bookDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> findAll() {

        List<BookResponse> response = bookService.findAll();

        Map<String, Object> mapData = new HashMap<>();

        mapData.put("data", response);
        mapData.put("size", response.size());

        return ResponseEntity.ok(mapData);

    }

    @GetMapping("/categories/{ide}/books")
    public ResponseEntity<Map<String, Object>> findAllByCategory(@PathVariable Integer ide) {

        List<BookResponse> response = bookService.findAllByCategory(ide);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", response);
        mapData.put("size", response.size());

        return ResponseEntity.ok(mapData);

    }

    @DeleteMapping("/books/{ide}")
    public ResponseEntity<?> delete(@PathVariable Integer ide) {

        LOGGER.debug(String.format("Book to Delete %s: ", ide));

        bookService.delete(ide);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/books/{ide}")
    public ResponseEntity<BookResponse> update(@PathVariable Integer ide,
                                               @Valid @RequestBody BookDTO bookDTO) {
        BookResponse response = bookService.update(bookDTO, ide);

        return ResponseEntity.ok(response);

    }

}
