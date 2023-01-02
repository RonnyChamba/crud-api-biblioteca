package com.ideas.org.crud.security.controller;


import com.ideas.org.crud.security.jwt.JwtUtils;
import com.ideas.org.crud.security.dto.AuthUser;
import com.ideas.org.crud.security.dto.JwtDTO;
import com.ideas.org.crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
//
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtils jwtUtils;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@Valid @RequestBody AuthUser authUser) {

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
//        );
//
//        final UserDetails user = userDetailsService.loadUserByUsername(authUser.getUsername());
//        if (user != null) {
//
//            JwtDTO jwtDTO = new JwtDTO();
//            jwtDTO.setToken(jwtUtils.generateToken(user));
//            return ResponseEntity.ok(jwtDTO);
//        }
//        return ResponseEntity.status(400).body("");

        JwtDTO jwtDTO = userService.login(authUser);
        return ResponseEntity.ok(jwtDTO);

    }


}
