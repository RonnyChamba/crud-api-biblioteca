package com.ideas.org.crud.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class AuthUser {

    @NotNull
    @NotBlank
    private  String username;

    @NotNull
    @NotBlank
    private  String password;

}
