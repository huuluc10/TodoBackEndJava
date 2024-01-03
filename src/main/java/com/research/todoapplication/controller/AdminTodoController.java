package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.Todo;
import com.research.todoapplication.model.TodoRequest;
import com.research.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/{userId}/todos")
public class AdminTodoController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ResponeAPI> getAllTodoByUser(@PathVariable long userId) {
        return userService.selectAllTodo(userId);
    }

    @PostMapping
    public ResponseEntity<ResponeAPI> createTodoByUser(@PathVariable long userId, @RequestBody TodoRequest todoRequest) {
        return userService.insertTodo(userId, todoRequest);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> deleteTodoByUser(@PathVariable long userId, @PathVariable long todoId) {
        return userService.deleteTodoByTodoId(userId, todoId);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> updateTodoByUser(@PathVariable long todoId, @RequestBody TodoRequest todoRequest) {
        return userService.updateTodoByTodoId(todoId, todoRequest);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ResponeAPI> getTodo(@PathVariable long userId, @PathVariable long todoId) {
        return userService.selectTodo(userId, todoId);
    }
}
