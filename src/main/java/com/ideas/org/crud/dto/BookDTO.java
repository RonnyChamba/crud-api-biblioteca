package com.ideas.org.crud.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO  implements Serializable{

    private  Integer ide;

    @NotNull
    @NotBlank
    private  String title;

    private  String description;

    @NotNull
    @NotBlank
    private String isbn;

    private  int pagesNumber;

    private  String photo;

    private String publicationDate;

    @NotNull
    private Integer category;
}
