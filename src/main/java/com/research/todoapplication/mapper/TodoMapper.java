package com.research.todoapplication.mapper;

import java.util.List;

import com.research.todoapplication.model.Todo;

public interface TodoMapper {
    int insertTodo(long userId, long todoId, String content, short isComplete);
    int updateTodo(Todo todo);
    int deleteTodo(long userId, long todoId);
    List<Todo> selectTodo(long userId, long todoId);
    long getMaxTodoId();
}
