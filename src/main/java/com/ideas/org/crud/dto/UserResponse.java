package com.ideas.org.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private Integer ide;

    private String username;

    private List<String> roles;
}
