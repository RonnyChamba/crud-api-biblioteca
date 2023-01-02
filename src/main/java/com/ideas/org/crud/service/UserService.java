package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.UserDTO;
import com.ideas.org.crud.dto.UserResponse;
import com.ideas.org.crud.security.dto.AuthUser;
import com.ideas.org.crud.security.dto.JwtDTO;

import java.util.List;

public interface UserService {

    UserResponse save(UserDTO  userDTO);

    List<UserResponse> finAll();
    JwtDTO login(AuthUser authUser);

    void delete(Integer ide);


}
