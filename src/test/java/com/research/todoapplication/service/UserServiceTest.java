package com.research.todoapplication.service;

import com.research.todoapplication.model.ResponeAPI;
import com.research.todoapplication.model.User;
import com.research.todoapplication.repository.TodoRepository;
import com.research.todoapplication.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TodoRepository todoRepository;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void selectAllUser() {
        ResponeAPI responeAPI = userService.selectAllUser().getBody();

        assert responeAPI.getCode() == 200;
        assert responeAPI.getMessage().equals("Success");
        assert responeAPI.getData() != null;
    }

    @Test
    public void selectUserAdmin() {
        ResponeAPI responeAPI =  userService.selectUser(1, "*").getBody();
        User user = (User) responeAPI.getData();
        assert responeAPI.getCode() == 200;
        assert responeAPI.getMessage().equals("Success");
        assert user.getUsername().equals("admin");
    }
}
