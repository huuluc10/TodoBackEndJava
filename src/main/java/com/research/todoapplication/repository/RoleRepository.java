package com.research.todoapplication.repository;

import com.research.todoapplication.config.MyBaticsConfig;
import com.research.todoapplication.mapper.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class RoleRepository {
    private final RoleMapper roleMapper;
    private final SqlSession sqlSession;
    private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);


    public RoleRepository() {

        this.sqlSession = Objects.requireNonNull(MyBaticsConfig.getSqlSessionFactory()).openSession();
        this.roleMapper = sqlSession.getMapper(RoleMapper.class);
        logger.info("RoleRepository created");
    }

    public String selectRole(int roleId) {
        try {
            logger.info("RoleRepository: Select role with roleId = {}", roleId);
            return roleMapper.select(roleId);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
