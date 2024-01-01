package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.exception.CustomException;
import com.research.todoapplication.mapper.UserMapper;
import com.research.todoapplication.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepository {
    private final UserMapper userMapper;
    private final SqlSession sqlSession;
    private static final String USER_NOT_FOUND = "User not found";


    public UserRepository() {
        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public int insert(Map<String, Object> params) {
        try {
            User user = (User) params.get("user");
            int result = userMapper.insertUser(user);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            System.out.println(new CustomException("User already exists", e.getMessage()));
            return -1;
        }
    }
    public int update(User user) {
        try {
            int result = userMapper.updateUser(user);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            System.out.println(new CustomException(USER_NOT_FOUND, e.getMessage()));
            return -1;
        }

    }

    public int delete(long userId) {
        try {
            int result = userMapper.deleteUser(userId);
            sqlSession.commit();
            return result;
        }
        catch (Exception e) {
            System.out.println(new CustomException(USER_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }

    public List<User> getAll(Map<String, String> params) {
        try {
            long userId = Long.parseLong(params.get("userId"));
            return userMapper.selectUser(userId);
        } catch (Exception e) {
            System.out.println(new IOException("Error when get all user" +  e.getMessage()));
            return Collections.emptyList();
        }
    }

    public User get(Map<String, String> params) {
        try {
            long userId = Long.parseLong(params.get("userId"));
            return userMapper.selectUser(userId).get(0);
        } catch (Exception e) {
            System.out.println(new IOException("Error when get all user" +  e.getMessage()));
            return null;
        }
    }

    public long getMaxUserId() {
        try {
            return userMapper.getMaxUserId();
        }
        catch (Exception e) {
            System.out.println(new CustomException(USER_NOT_FOUND, e.getMessage()));
            return -1;
        }
    }
}
