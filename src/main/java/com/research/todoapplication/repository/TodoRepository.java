package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.exception.CustomException;
import com.research.todoapplication.mapper.TodoMapper;
import com.research.todoapplication.model.Todo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class TodoRepository {
    private final TodoMapper todoMapper;
    private final SqlSession sqlSession;
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoRepository() {
        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.todoMapper = sqlSession.getMapper(TodoMapper.class);
    }

    public int delete(long userId, long todoId) {
        try {
            int result = todoMapper.deleteTodo(userId, todoId);
            sqlSession.commit();
            return result;
        }
        catch (Exception e) {
            System.out.println(new CustomException(TODO_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }

    public int update(Todo todo) {
        try {
            int result = todoMapper.updateTodo(todo);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            System.out.println(new CustomException(TODO_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }

    public int insert(Map<String, Object> params) {
        try {
            long userId = Long.parseLong(params.get("userId").toString());
            Todo todo = (Todo) params.get("todo");
            int result = todoMapper.insertTodo(userId, todo.getTodoId(), todo.getContent(), todo.getIsComplete());
            sqlSession.commit();
            return result;
        }
        catch (Exception e) {
            System.out.println(new CustomException(TODO_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }

    public List<Todo> getTodo(long userId, long todoId) {
        try {
            return todoMapper.selectTodo(userId, todoId);
        } catch (Exception e) {
            System.out.println(new IOException("Error when get all todo" +  e.getMessage()));
            return Collections.emptyList();
        }
    }

    public long getMaxTodoId() {
        try {
            return todoMapper.getMaxTodoId();
        }
        catch (Exception e) {
            System.out.println(new CustomException(TODO_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }
}
