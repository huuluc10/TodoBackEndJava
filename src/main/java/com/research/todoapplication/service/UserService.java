package com.research.todoapplication.service;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.Todo;
import com.research.todoapplication.model.User;
import com.research.todoapplication.repository.TodoRepository;
import com.research.todoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;

    private static final String NOT_FOUND = "Not found";
    private static final String BAD_REQUEST = "Bad request";
    private static final String SUCCESS = "Success";
    private static final String USER_ID = "userId";

    public ResponseEntity<ResponeAPI> selectAllUser() {
        List<User> users =  userRepository.getAll(-1, "*");

        if (users.isEmpty()) {
            return new ResponseEntity<>(new ResponeAPI(404, NOT_FOUND, null), org.springframework.http.HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, users), org.springframework.http.HttpStatus.OK);
    }

    public ResponseEntity<ResponeAPI> selectUser(long userId, String username) {
        User user =  userRepository.get(userId, username);

        if (user == null) {
            return new ResponseEntity<>(new ResponeAPI(404, NOT_FOUND, null), org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, user), org.springframework.http.HttpStatus.OK);
    }

    public ResponseEntity<ResponeAPI> insertUser(User user) {
        user.setUserId(getNewUserId());
        HashMap<String, Object> params = new HashMap<>();
        params.put("user", user);
        int result = userRepository.insert(params);
        if (result == -1) {
            return new ResponseEntity<>(new ResponeAPI(400, BAD_REQUEST, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponeAPI(201, "Created", "Create user successfully"), org.springframework.http.HttpStatus.OK);
    }

    public ResponseEntity<ResponeAPI> updateUser(User user) {
        int result = userRepository.update(user);
        if (result == 1) {
            return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, "Update user successfully"), org.springframework.http.HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponeAPI(400, BAD_REQUEST, null), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponeAPI> deleteUser(long userId) {
        userRepository.delete(userId);
        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, "Delete user successfully"), org.springframework.http.HttpStatus.OK);
    }

    public ResponseEntity<ResponeAPI> selectAllTodo(long userId) {
        List<Todo> todos = todoRepository.getTodo(userId, -1);

        if (todos.isEmpty()) {
            return new ResponseEntity<>(new ResponeAPI(404, NOT_FOUND, null), org.springframework.http.HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, todos), org.springframework.http.HttpStatus.OK);
    }

    public ResponseEntity<ResponeAPI> selectTodo(long userId, long todoId) {
        List<Todo> todos = todoRepository.getTodo(userId, todoId);
        if (todos.isEmpty()) {
            return new ResponseEntity<>(new ResponeAPI(404, NOT_FOUND, null), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, todos), org.springframework.http.HttpStatus.OK);
    }

    //delete todo
    public ResponseEntity<ResponeAPI> deleteTodoByTodoId(long userId, long todoId) {
        todoRepository.delete(userId, todoId);
        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, "Delete todo successfully"), org.springframework.http.HttpStatus.OK);
    }

    //update todo
    public ResponseEntity<ResponeAPI> updateTodoByTodoId(Todo todo) {
        System.out.println();
        int result = todoRepository.update(todo);
        if (result == -1) {
            return new ResponseEntity<>(new ResponeAPI(400, BAD_REQUEST, null), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponeAPI(200, SUCCESS, "Update todo successfully"), org.springframework.http.HttpStatus.OK);
    }

    //insert todo
    public ResponseEntity<ResponeAPI> insertTodo(long userId, Todo t) {
        Todo todo = t;
        todo.setTodoId(getNewTodoId());
        HashMap<String, Object> params = new HashMap<>();
        params.put(USER_ID, userId);
        params.put("todo", todo);
        todoRepository.insert(params);
        return new ResponseEntity<>(new ResponeAPI(201, "Created", "Create todo successfully"), org.springframework.http.HttpStatus.OK);
    }

    //get new userId
    public long getNewUserId() {
        return userRepository.getMaxUserId() + 1;
    }

    //get new todoId
    public long getNewTodoId() {
        return todoRepository.getMaxTodoId() + 1;
    }

    public long getUserId(String username) {
        return userRepository.getUserId(username);
    }
}
