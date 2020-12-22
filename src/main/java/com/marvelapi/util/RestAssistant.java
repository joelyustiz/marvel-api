package com.marvelapi.util;

import com.marvelapi.domain.ResponseREST;

public class RestAssistant {
    
    private int cod;
    private String msg;
    private Object response;

    public void addMsgOK(String msg) {
        this.msg = msg;
    }

    public void addMsgErrorDataSource() {
        this.addMsgError("Sin datos");
    }

    public void addMsgErrorSQLGrammar() {
        this.addMsgError("Error en la instrucción");
    }

    public void addMsgError(String msg) {
        this.msg = msg;
        this.cod = 1;
    }

    public void addSpecialMsg(String msg) {
        this.msg = msg;
        this.cod = 3;
    }

    public ResponseREST getResponse() {
        return new ResponseREST(cod, msg, response);
    }

    public void addResponse(Object response) {
        this.response = response;
    }
}
