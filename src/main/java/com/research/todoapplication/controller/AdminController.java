package com.research.todoapplication.controller;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.Todo;
import com.research.todoapplication.model.User;
import com.research.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/admin/users")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ResponeAPI> getAllUser() {
        return userService.selectAllUser();
    }

    @PostMapping
    public ResponseEntity<ResponeAPI> createUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponeAPI> getUser(@PathVariable long userId) {
        return userService.selectUser(userId, "*");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponeAPI> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponeAPI> deleteUser(@PathVariable long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<ResponeAPI> getAllTodoByUser(@PathVariable long userId) {
        return userService.selectAllTodo(userId);
    }

    @PostMapping("/{userId}/todos")
    public ResponseEntity<ResponeAPI> createTodoByUser(@PathVariable long userId, @RequestBody Todo todo) {
        return userService.insertTodo(userId, todo);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<ResponeAPI> deleteTodoByUser(@PathVariable long userId, @PathVariable long todoId) {
        return userService.deleteTodoByTodoId(userId, todoId);
    }

    @PutMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<ResponeAPI> updateTodoByUser(@PathVariable long todoId, @RequestBody Todo todo) {
        return userService.updateTodoByTodoId(todo);
    }

    @GetMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<ResponeAPI> getTodo(@PathVariable long userId, @PathVariable long todoId) {
        return userService.selectTodo(userId, todoId);
    }


}
