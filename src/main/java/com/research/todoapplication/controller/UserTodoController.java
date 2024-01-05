package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.TodoRequest;
import com.research.todoapplication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/todos")
@Tag(name = "User Todo")
@SecurityRequirement(name = "auth-api")
public class UserTodoController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<ResponeAPI> getAllTodoByUser(Principal principal) {
        return userService.selectAllTodo(getUserId(principal));
    }

    @PostMapping
    public ResponseEntity<ResponeAPI> createTodo(Principal principal,@RequestBody TodoRequest todoRequest) {
        return userService.insertTodo(getUserId(principal), todoRequest);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> deleteTodoByUser(Principal principal, @PathVariable long todoId) {
        return userService.deleteTodoByTodoId(getUserId(principal), todoId);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> updateTodoByUser(@PathVariable long todoId, @RequestBody TodoRequest todoRequest) {
        return userService.updateTodoByTodoId(todoId, todoRequest);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> getTodo(Principal principal, @PathVariable long todoId) {
        return userService.selectTodo(getUserId(principal), todoId);
    }
    private long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }
}
