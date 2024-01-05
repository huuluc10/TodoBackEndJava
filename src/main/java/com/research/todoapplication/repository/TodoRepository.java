package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.exception.CustomException;
import com.research.todoapplication.mapper.TodoMapper;
import com.research.todoapplication.model.Todo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class TodoRepository {
    private final TodoMapper todoMapper;
    private final SqlSession sqlSession;
    private static final String TODO_NOT_FOUND = "Todo not found";
    private final Logger logger = LoggerFactory.getLogger(TodoRepository.class);
    private static final String TAG = "TodoRepository: ";

    public TodoRepository() {
        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.todoMapper = sqlSession.getMapper(TodoMapper.class);
        logger.info("TodoRepository created");
    }

    public int delete(long userId, long todoId) {
        try {
            int result = todoMapper.deleteTodo(userId, todoId);
            sqlSession.commit();
            logger.info(TAG, "Delete todo with todoId = %d of userId = %d success", todoId, userId);
            return result;
        }
        catch (Exception e) {
            logger.error(TAG, new CustomException(TODO_NOT_FOUND, e.getMessage()).toString());
            return -1;
        }
    }

    public int update(Todo todo) {
        try {
            int result = todoMapper.updateTodo(todo);
            sqlSession.commit();
            logger.info(TAG, "Update todo with todoId = %d", todo.getTodoId());
            return result;
        } catch (Exception e) {
            logger.error(TAG, new CustomException(TODO_NOT_FOUND, e.getMessage()).toString());
            return -1;
        }
    }

    public int insert(Map<String, Object> params) {
        try {
            long userId = Long.parseLong(params.get("userId").toString());
            Todo todo = (Todo) params.get("todo");
            int result = todoMapper.insertTodo(userId, todo.getTodoId(), todo.getContent(), todo.getIsComplete());
            sqlSession.commit();
            logger.info(TAG, "Insert todo with todoId = %d", todo.getTodoId());
            return result;
        }
        catch (Exception e) {
            logger.error(TAG, new CustomException(TODO_NOT_FOUND, e.getMessage()).toString());
            return -1;
        }
    }

    public List<Todo> getTodo(long userId, long todoId) {
        try {
            logger.info("TodoRepository: Get todo todo");
            return todoMapper.selectTodo(userId, todoId);
        } catch (Exception e) {
            logger.error(TAG, new CustomException("Error when get todo", e.getMessage()).toString());
            return Collections.emptyList();
        }
    }

    public long getMaxTodoId() {
        try {
            long maxTodoId = todoMapper.getMaxTodoId();
            logger.info(TAG, "Get max todo id = ", maxTodoId);
            return maxTodoId;
        }
        catch (Exception e) {
            logger.error(TAG, new CustomException(TODO_NOT_FOUND, e.getMessage()).toString());
            return -1;
        }
    }
}
