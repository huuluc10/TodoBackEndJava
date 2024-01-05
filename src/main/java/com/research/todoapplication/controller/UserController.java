package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.UserRequest;
import com.research.todoapplication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User")
@SecurityRequirement(name = "auth-api")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<ResponeAPI> getInfo(Principal principal) {
        return userService.selectUser(-1, principal.getName());
    }

    @PutMapping
    public ResponseEntity<ResponeAPI> updateUser(Principal principal, @RequestBody UserRequest userRequest) {
        return userService.updateUser(getUserId(principal), userRequest);
    }

    @DeleteMapping
    public ResponseEntity<ResponeAPI> deleteUser(Principal principal) {
        return userService.deleteUser(getUserId(principal));
    }
    private long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

}
