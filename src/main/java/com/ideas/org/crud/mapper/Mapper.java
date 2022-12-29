package com.ideas.org.crud.mapper;

import com.ideas.org.crud.dto.BookDTO;
import com.ideas.org.crud.dto.BookResponse;
import com.ideas.org.crud.dto.CategoryDTO;
import com.ideas.org.crud.dto.CategoryResponse;
import com.ideas.org.crud.entities.Book;
import com.ideas.org.crud.entities.Category;
import com.ideas.org.crud.util.UtilFunction;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Category mapperToEntity(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public CategoryResponse mapperToDTO(Category category) {

        CategoryResponse response = new CategoryResponse();
        response.setIde(category.getIde());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    public Book mapperToEntity(BookDTO bookDTO) {

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
        book.setPagesNumber(bookDTO.getPagesNumber());
        book.setPhoto(bookDTO.getPhoto());
        try {
            book.setPublicationDate(UtilFunction.convertStringToDate(bookDTO.getPublicationDate()));
        } catch (Exception e) {

        }

        return book;
    }

    public BookResponse mapperToDTO(Book book) {

        BookResponse response = new BookResponse();
        response.setIde(book.getIde());
        response.setTitle(book.getTitle());
        response.setDescription(book.getDescription());
        response.setIsbn(book.getIsbn());
        response.setCategory(book.getCategory().getName());
        return response;
    }

    public void mapperToUpdateEntity(Book bookCurrent, BookDTO bookDTO) {

        bookCurrent.setTitle(bookDTO.getTitle());
        bookCurrent.setIsbn(bookDTO.getIsbn());
        bookCurrent.setPagesNumber(bookDTO.getPagesNumber());
        if (bookDTO.getDescription() != null) {
            bookCurrent.setDescription(bookDTO.getDescription());
        }

        if (bookDTO.getPhoto() != null) {
            bookCurrent.setPhoto(bookDTO.getPhoto());
        }

        if (bookDTO.getPublicationDate() != null) {
            try {
                bookCurrent.setPublicationDate(UtilFunction.convertStringToDate(bookDTO.getPublicationDate()));
            } catch (Exception e) {

            }
        }

    }

}
