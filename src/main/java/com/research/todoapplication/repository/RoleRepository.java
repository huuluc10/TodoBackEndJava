package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.mapper.RoleMapper;
import com.research.todoapplication.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class RoleRepository {
    private final RoleMapper roleMapper;
    private final SqlSession sqlSession;


    public RoleRepository() {
        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    public String selectRole(int roleId) {
        return roleMapper.select(roleId);
    }
}
