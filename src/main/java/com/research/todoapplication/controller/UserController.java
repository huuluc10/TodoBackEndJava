package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.Todo;
import com.research.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<ResponeAPI> getInfo(Principal principal) {
        return userService.selectUser(-1, principal.getName());
    }
    @GetMapping("/todos")
    public ResponseEntity<ResponeAPI> getAllTodoByUser(Principal principal) {
        return userService.selectAllTodo(getUserId(principal));
    }

    @PostMapping("/todos")
    public ResponseEntity<ResponeAPI> createTodo(Principal principal,@RequestBody Todo todo) {
        return userService.insertTodo(getUserId(principal), todo);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<ResponeAPI> deleteTodoByUser(Principal principal, @PathVariable long todoId) {
        return userService.deleteTodoByTodoId(getUserId(principal), todoId);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<ResponeAPI> updateTodoByUser(@RequestBody Todo todo) {
        return userService.updateTodoByTodoId(todo);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<ResponeAPI> getTodo(Principal principal, @PathVariable long todoId) {
        return userService.selectTodo(getUserId(principal), todoId);
    }

    private long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

}
