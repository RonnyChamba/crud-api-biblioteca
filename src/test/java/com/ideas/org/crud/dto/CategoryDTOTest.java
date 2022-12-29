package com.ideas.org.crud.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDTOTest {

    @Test
    void testCategoryConstructor(){

        CategoryDTO  categoryDTO = new CategoryDTO(
                1, "Drama", "Nothing description"
        );
        
        assertEquals(1, categoryDTO.getIde());
        assertEquals("Drama", categoryDTO.getName());
        assertEquals("Nothing description", categoryDTO.getDescription());
    }


    @Test
    void testBookConstructor(){

        BookDTO  bookDTO = new BookDTO(
                1, "Mil and a Tonight", "Nothing description", "1234567890", 10,"Nothing Photo", "2020-10-2", 1
        );

        assertEquals(1, bookDTO.getIde());
        assertEquals("Mil and a Tonight", bookDTO.getTitle());
        assertEquals("Nothing description", bookDTO.getDescription());
        assertEquals("1234567890", bookDTO.getIsbn());
        assertEquals(10, bookDTO.getPagesNumber());
        assertEquals("Nothing Photo", bookDTO.getPhoto());
        assertEquals("2020-10-2", bookDTO.getPublicationDate());
        assertEquals(1, bookDTO.getCategory());
    }

}