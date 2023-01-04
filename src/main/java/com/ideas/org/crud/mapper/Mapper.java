package com.ideas.org.crud.mapper;

import com.ideas.org.crud.dto.*;
import com.ideas.org.crud.entities.Book;
import com.ideas.org.crud.entities.Category;
import com.ideas.org.crud.entities.User;
import com.ideas.org.crud.util.UtilFunction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {


    public Category mapperToEntity(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public BookDTO mapperToUpdateDTO(Book book) {

        BookDTO bookDTO = new BookDTO();

        bookDTO.setIde(book.getIde());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationDate(UtilFunction.convertDateToString(book.getPublicationDate()));
        bookDTO.setPagesNumber(book.getPagesNumber());
        bookDTO.setCategory(book.getCategory().getIde());
        return bookDTO;

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
        bookCurrent.setDescription(bookDTO.getDescription());

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

    public User mapperToEntity(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;

    }

    public UserResponse mapperToDTO(User user) {

        UserResponse response = new UserResponse();
        response.setIde(user.getIde());
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles().stream()
                .map(item -> item.getRolEnun().name())
                .collect(Collectors.toList()));

        return response;

    }

    public List<UserResponse> mapperToDTO(List<User> user) {
        return user.stream()
                .map(this::mapperToDTO).collect(Collectors.toList());
    }

    public List<CategoryBookResponse> mapperFetchToDTO(List<Category> categories) {
        List<CategoryBookResponse> categoryBookRes = new ArrayList<>();
        categories.forEach(item -> {

            CategoryBookResponse cateResp = new CategoryBookResponse();

            cateResp.setIde(item.getIde());
            cateResp.setDescription(item.getDescription());
            cateResp.setName(item.getName());
            cateResp.setNumberBooks(item.getBooks().size());
            categoryBookRes.add(cateResp);
        });
        return categoryBookRes;

    }

}
