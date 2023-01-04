package com.ideas.org.crud.security.controller;


import com.ideas.org.crud.controller.UserController;
import com.ideas.org.crud.dto.UserDTO;
import com.ideas.org.crud.dto.UserResponse;
import com.ideas.org.crud.security.jwt.JwtUtils;
import com.ideas.org.crud.security.dto.AuthUser;
import com.ideas.org.crud.security.dto.JwtDTO;
import com.ideas.org.crud.service.UserService;
import com.ideas.org.crud.util.UtilFunction;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = UtilFunction.CROSS_ORIGIN)
public class AuthenticationController {
    private final UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);
    @PostMapping("/users")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserDTO userDTO) {

        LOGGER.debug(String.format("User to save: %s", userDTO));
        UserResponse response = userService.save(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@Valid @RequestBody AuthUser authUser) {

        JwtDTO jwtDTO = userService.login(authUser);
        return ResponseEntity.ok(jwtDTO);

    }


}
