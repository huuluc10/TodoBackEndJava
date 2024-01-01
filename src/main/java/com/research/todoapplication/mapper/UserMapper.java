package com.research.todoapplication.mapper;

import com.research.todoapplication.model.User;

import java.util.List;

public interface UserMapper {
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(long userId);
    List<User> selectUser(long userId);
    long getMaxUserId();
}
