package com.research.todoapplication.controller;

import com.research.todoapplication.model.*;
import com.research.todoapplication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private static final String TAG = "AdminController: UserRequest = {}";

    @GetMapping
    public ResponseEntity<ResponeAPI> getAllUser() {
        logger.info("AdminController: Get all user");
        return userService.selectAllUser();
    }

    @PostMapping
    public ResponseEntity<ResponeAPI> createUser(@RequestBody UserRequest userRequest) {
        logger.info("AdminController: Create user");
        logger.info(TAG, userRequest);
        return userService.insertUser(userRequest);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponeAPI> getUser(@PathVariable long userId) {
        logger.info("AdminController: Get user by id = {}", userId);
        logger.info(TAG, userId);
        return userService.selectUser(userId, "*");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponeAPI> updateUser(@PathVariable long userId, @RequestBody UserRequest userRequest) {
        logger.info("AdminController: Update user by id = {}", userId);
        logger.info(TAG, userRequest);
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponeAPI> deleteUser(@PathVariable long userId) {
        logger.info("AdminController: Delete user by id = {}", userId);
        return userService.deleteUser(userId);
    }




}
