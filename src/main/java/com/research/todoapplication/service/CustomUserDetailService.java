package com.research.todoapplication.service;

import com.research.todoapplication.model.User;
import com.research.todoapplication.repository.RoleRepository;
import com.research.todoapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.get(-1,username);
        UserDetails userDetails;

        if (user != null) {
            int roleId = user.getRoleId();
            String role = roleRepository.selectRole(roleId);
             userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(role)
                    .build();
             logger.info("User found");
             logger.info("Infomation of user");
             logger.info("Username: {}",userDetails.getUsername());
             logger.info("Role: {}",userDetails.getAuthorities());
        } else {
            logger.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }
}
