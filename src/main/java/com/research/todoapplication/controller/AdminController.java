package com.research.todoapplication.controller;

import com.research.todoapplication.model.*;
import com.research.todoapplication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/admin/users")
@Tag(name = "Admin")
@SecurityRequirement(name = "auth-api")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ResponeAPI> getAllUser() {
        return userService.selectAllUser();
    }

    @PostMapping
    public ResponseEntity<ResponeAPI> createUser(@RequestBody UserRequest userRequest) {
        return userService.insertUser(userRequest);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponeAPI> getUser(@PathVariable long userId) {
        return userService.selectUser(userId, "*");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponeAPI> updateUser(@PathVariable long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponeAPI> deleteUser(@PathVariable long userId) {
        return userService.deleteUser(userId);
    }




}
