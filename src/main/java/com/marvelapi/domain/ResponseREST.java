package com.marvelapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ResponseREST {

    private Object response;
    private String msg;
    private int code;
    private LocalDateTime salt;

    public ResponseREST() {
        this.salt = LocalDateTime.now();
    }

    public ResponseREST(int code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public ResponseREST(int code, String msg, Object response) {
        this(code, msg);
        this.response = response;
    }

}

