package com.research.todoapplication.model;

import lombok.Data;

@Data
public class User {
    private long userId;
    private String username;
    private int roleId;
    private String password;
}
