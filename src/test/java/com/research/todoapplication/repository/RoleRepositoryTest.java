package com.research.todoapplication.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleRepository.class})
@ExtendWith(SpringExtension.class)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void testSelectRoleAdmin() {
        String role = roleRepository.selectRole(1);

        assert(role.equals("ADMIN"));
    }
    @Test
    void testSelectRoleUser() {
        String role = roleRepository.selectRole(2);
        assert(role.equals("USER"));
    }
}
