package com.ideas.org.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse implements Serializable {


    private Integer ide;

    private String title;

    private String description;

    private String isbn;
//
//    private  int pagesNumber;
//
//    private  String photo;
//
//    private String publicationDate;

    private String category;
}
