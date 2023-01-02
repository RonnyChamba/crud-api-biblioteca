package com.ideas.org.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Integer ide;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 25)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 25)
    private String password;

    @NotNull
    @NotBlank
    private String roles;
}
