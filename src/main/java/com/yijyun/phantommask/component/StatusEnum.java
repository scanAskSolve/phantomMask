package com.yijyun.phantommask.component;

import lombok.Data;

public enum StatusEnum {
    SUCCESS(200,"OK"),
    PARAM_INSERT_ERROR(401,"wrong input format");

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    Integer code;
    String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
