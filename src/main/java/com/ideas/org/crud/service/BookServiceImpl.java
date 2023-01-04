package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.BookDTO;
import com.ideas.org.crud.dto.BookResponse;
import com.ideas.org.crud.entities.Book;
import com.ideas.org.crud.exeption.DuplicatedResourceException;
import com.ideas.org.crud.exeption.NotDataAccessException;
import com.ideas.org.crud.exeption.NotFoundException;
import com.ideas.org.crud.mapper.Mapper;
import com.ideas.org.crud.repository.BookRepository;
import com.ideas.org.crud.repository.CategoryRepository;
import com.ideas.org.crud.util.UtilFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    private static final Logger LOGGER = LogManager.getLogger(BookService.class);

    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;

    private Mapper mapper;

    public BookServiceImpl(BookRepository bookRepository, Mapper mapper, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public BookResponse save(BookDTO bookDTO) {

        try {

            if (bookRepository.existsByTitleIgnoreCaseAndIsbn(bookDTO.getTitle(), bookDTO.getIsbn())) {
                throw new DuplicatedResourceException(String.format("Libro %s con isbn %s ya existe",
                        bookDTO.getTitle(), bookDTO.getIsbn()));
            }

            Book book = mapper.mapperToEntity(bookDTO);
            book.setCategory(categoryRepository.findById(bookDTO.getCategory()).orElseThrow(
                    () -> new NotFoundException(String.format("Categoria %s no existe", bookDTO.getCategory()))
            ));

            return mapper.mapperToDTO(bookRepository.save(book));
        } catch (DataAccessException e) {

            LOGGER.error("Error al guardar categoria", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {

        try {
            List<Book> books = bookRepository.findAllFetchCategory();
            return books.stream()
                    .map(item -> mapper.mapperToDTO(item))
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {

            LOGGER.error("Error al list category", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findByIde(Integer ide) {

        try {

            Book bookFound = bookRepository.findById(ide)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("Book %s not exist", ide)
                    ));

            return mapper.mapperToUpdateDTO(bookFound);

        } catch (DataAccessException e) {

            LOGGER.error("Error to search book", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }


    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> findAllByCategory(Integer ide) {

        try {
            List<Book> books = bookRepository.findAllFetchByCategory(ide);
            return books.stream()
                    .map(item -> mapper.mapperToDTO(item))
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {

            LOGGER.error("Error al list category", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional
    public void delete(Integer ide) {

        try {

            if (bookRepository.existsById(ide)) {
                bookRepository.deleteById(ide);
                return;
            }

            throw new NotFoundException(String.format("Book %s not found", ide));
        } catch (DataAccessException e) {

            LOGGER.error("Error to delete a category", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }

    @Override
    @Transactional
    public BookResponse update(BookDTO bookDTO, Integer ide) {

        try {

            Book book = bookRepository.findById(ide)
                    .orElseThrow(
                            () -> new NotFoundException(String.format("Book %s not found", ide))
                    );

            mapper.mapperToUpdateEntity(book, bookDTO);

            if (bookDTO.getCategory() != null) {

                book.setCategory(categoryRepository.findById(bookDTO.getCategory()).orElseThrow(
                        () -> new NotFoundException(String.format("Categoria %s no existe", bookDTO.getCategory()))
                ));
            }

            return mapper.mapperToDTO(bookRepository.save(book));

        } catch (DataAccessException e) {

            LOGGER.error("Error to update a category", e);
            throw new NotDataAccessException("Error al ejecutar operación");
        }
    }
}
