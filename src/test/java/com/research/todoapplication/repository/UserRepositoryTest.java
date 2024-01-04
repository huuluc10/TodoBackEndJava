package com.research.todoapplication.repository;

import com.research.todoapplication.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = {UserRepository.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeAll
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void getAllUsersTest() {
        List<User> userList = userRepository.getAll(-1, "*");
        assert(!userList.isEmpty());
    }

    @Test
    void getUser1Test() {
        User user = userRepository.get(1, "*");
        assert(user != null);
        assert (user.getUserId() == 1);
        assert (user.getUsername().equals("lucnh"));
        assert (user.getRoleId() == 2);
    }

    @Test
    void getMaxUserTest() {
        long maxUserId = userRepository.getMaxUserId();
        assert(maxUserId > 0);
    }

    @Test
    void getIdUserTest() {
        long userId = userRepository.getUserId("lucnh");
        assert(userId == 1);
    }

    @Test
    void insertUserFailTest() {
        User user = new User();
        user.setUserId(0);
        user.setUsername("test");
        user.setPassword("test");
        user.setRoleId(2);
        Map<String, Object> params = Map.of("user", user);
        int result = userRepository.insert(params);
        assert(result < 0);
    }

    @Test
    void insertUserSuccessTest() {
        User user = new User();
        user.setUserId(4);
        user.setUsername("test");
        user.setPassword("test");
        user.setRoleId(2);
        Map<String, Object> params = Map.of("user", user);
        int result = userRepository.insert(params);
        assert(result > 0);
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setUserId(4);
        user.setUsername("123");
        user.setPassword("test");
        user.setRoleId(2);
        int result = userRepository.update(user);
        assert(result > 0);
    }

    @Test
    void deleteUserTest() {
        userRepository.delete(4);
        User user = userRepository.get(4, "*");
        assert(user == null);
    }
}
