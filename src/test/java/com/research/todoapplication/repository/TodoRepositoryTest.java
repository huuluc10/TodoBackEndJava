package com.research.todoapplication.repository;

import com.research.todoapplication.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = {TodoRepository.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoRepositoryTest {

    private TodoRepository todoRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        todoRepositoryUnderTest = new TodoRepository();
    }


    @Test
    void getMaxTodoIdTest() {
        long id = todoRepositoryUnderTest.getMaxTodoId();
        assert (id > 0);
    }

    @Test
    void getTodoTest() {
        long userId = 2;
        long todoId = 2;
        assert (todoRepositoryUnderTest.getTodo(userId, todoId) != null);
    }

    @Test
    void insertTest() {
        long userId = 1;
        long todoId = 1;
        String content = "Add todo 4/1";
        short isComplete = 0;
        Todo todo = new Todo(todoId, content, isComplete);
        Map<String, Object> params = Map.of("userId", userId, "todo", todo);
        todoRepositoryUnderTest.insert(params);
        assert (todoRepositoryUnderTest.getTodo(1,1) != null);
    }

    @Test
    void updateTest() {
        long userId = 1;
        long todoId = 1;
        String content = "Add todo 4/1";
        short isComplete = 1;
        Todo todo = new Todo(todoId, content, isComplete);
        todoRepositoryUnderTest.update(todo);
        Todo todoInserted = todoRepositoryUnderTest.getTodo(1,1).getFirst();
        assert (todoInserted.getTodoId() == todo.getTodoId());
        assert (todoInserted.getContent().equals(todo.getContent()));
        assert (todoInserted.getIsComplete() == todo.getIsComplete());
    }

    @Test
    void deleteTest() {
        long userId = 1;
        long todoId = 1;
        todoRepositoryUnderTest.delete(userId, todoId);
        List<Todo> todoList = todoRepositoryUnderTest.getTodo(userId, todoId);
        assert (todoList.size() == 0);
    }
   
}
