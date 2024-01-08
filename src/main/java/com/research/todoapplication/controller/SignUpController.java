package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.UserRequest;
import com.research.todoapplication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@Tag(name = "Sign up")
@SecurityRequirement(name = "auth-api")
public class SignUpController {
    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(SignUpController.class);
    private static final String TAG = "SignUpController: UserRequest = {}";

    @PostMapping
    public ResponseEntity<ResponeAPI> signUp(@RequestBody UserRequest userRequest) {
        logger.info(TAG, userRequest);
        return userService.insertUser(userRequest);
    }
}
