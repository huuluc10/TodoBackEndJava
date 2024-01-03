package com.research.todoapplication.model;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private int roleId;
    private String password;
}
