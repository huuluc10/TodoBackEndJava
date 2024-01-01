package com.research.todoapplication.model;

import lombok.Data;

@Data
public class Todo {
    private long todoId;
    private String content;
    private short isComplete;
}
