package com.research.todoapplication.model;

import lombok.Data;

@Data
public class ResponeAPI {
    private int code;
    private String message;
    private Object data;

    public ResponeAPI(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
