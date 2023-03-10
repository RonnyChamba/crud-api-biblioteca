package com.ideas.org.crud.controller;

import com.ideas.org.crud.dto.UserDTO;
import com.ideas.org.crud.dto.UserResponse;
import com.ideas.org.crud.service.UserService;
import com.ideas.org.crud.util.UtilFunction;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = UtilFunction.CROSS_ORIGIN)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> findAll() {


        List<UserResponse> userList = userService.finAll();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", userList);
        mapData.put("size", userList.size());

        return ResponseEntity.ok(mapData);
    }





    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{ide}")
    public ResponseEntity<?> delete(@PathVariable Integer ide) {
        userService.delete(ide);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
