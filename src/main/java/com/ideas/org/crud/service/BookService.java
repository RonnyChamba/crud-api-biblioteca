package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.BookDTO;
import com.ideas.org.crud.dto.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse save(BookDTO bookDTO);

    List<BookResponse> findAll();

    List<BookResponse> findAllByCategory(Integer ide);

    void delete(Integer ide);

    BookResponse update(BookDTO bookDTO, Integer ide);
}
