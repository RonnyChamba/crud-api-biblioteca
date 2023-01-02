package com.ideas.org.crud.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;
}
