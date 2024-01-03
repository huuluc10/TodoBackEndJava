package com.research.todoapplication.model;

import lombok.Data;

@Data
public class TodoRequest {
    private String content;
    private short isComplete;
}
