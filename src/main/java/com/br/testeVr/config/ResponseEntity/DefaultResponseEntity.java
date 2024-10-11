package com.br.testeVr.config.ResponseEntity;

public class DefaultResponseEntity<T> {
    protected final String message;
    protected final T dados;

    public DefaultResponseEntity(String message, T dados) {
        this.message = message;
        this.dados = dados;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return dados;
    }
}
