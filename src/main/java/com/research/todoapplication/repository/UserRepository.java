package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.exception.CustomException;
import com.research.todoapplication.mapper.UserMapper;
import com.research.todoapplication.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepository {
    private final UserMapper userMapper;
    private final SqlSession sqlSession;
    private static final String USER_NOT_FOUND = "User not found";
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private static final String TAG = "UserRepository: ";
    public UserRepository() {
        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.userMapper = sqlSession.getMapper(UserMapper.class);
        logger.info("UserRepository created");
    }

    public int insert(Map<String, Object> params) {
        try {
            User user = (User) params.get("user");
            int result = userMapper.insertUser(user);
            logger.info(TAG, "Insert user with userId = ", user.getUserId());
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            logger.error(TAG, "Error when insert user with userId = ", ((User) params.get("user")).getUserId(), e.getMessage());
            return -1;
        }
    }
    public int update(User user) {
        try {
            int result = userMapper.updateUser(user);
            sqlSession.commit();
            logger.info(TAG, "Update user with userId = ", user.getUserId());
            return result;
        } catch (Exception e) {
            logger.error(TAG, "Error when update user with userId = ", user.getUserId(), e.getMessage());
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
            logger.error(new CustomException(USER_NOT_FOUND, e.getMessage()).toString());
            return -1;
        }
    }

    public List<User> getAll(long userId, String username) {
        try {
            logger.info("Get all user");
            return userMapper.selectUser(userId, username);
        } catch (Exception e) {
            logger.error(new CustomException("Error when get all user", e.getMessage()).toString());
            return Collections.emptyList();
        }
    }

    public User get(long userId, String username) {
        try {
            logger.info(TAG, "Get user with userId = ", userId, " and username = ", username);
            return userMapper.selectUser(userId, username).get(0);
        } catch (Exception e) {
            logger.error(TAG, "Error when get user with userId = ", userId, " and username = ", username, e.getMessage());
            return null;
        }
    }

    public long getMaxUserId() {
        try {
            logger.info(TAG, "Get max user id");
            return userMapper.getMaxUserId();
        }
        catch (Exception e) {
            logger.error(TAG, "Error when get max user id", e.getMessage());
            return -1;
        }
    }

    public long getUserId(String username) {
        try {

            logger.info(TAG, "Get user id with username = ", username);
            User user = userMapper.selectUser(-1, username).get(0);
            return user.getUserId();
        }
        catch (Exception e) {
            logger.error(TAG, "Error when get user id with username = ", username, e.getMessage());
            return -1;
        }
    }
}
