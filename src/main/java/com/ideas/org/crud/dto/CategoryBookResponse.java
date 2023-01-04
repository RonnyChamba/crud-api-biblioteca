package com.ideas.org.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBookResponse {

    private Integer ide;

    private  String name;

    private  String description;

    private  Integer numberBooks;
}
