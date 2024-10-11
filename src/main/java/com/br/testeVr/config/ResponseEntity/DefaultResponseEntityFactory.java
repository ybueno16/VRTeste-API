package com.br.testeVr.config.ResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


public class DefaultResponseEntityFactory {
    public static ResponseEntity<DefaultResponseEntity> create(
            String pMessage, Object pObject, HttpStatus pHttpStatus) {
        System.out.println(pMessage);

        return new ResponseEntity<DefaultResponseEntity>(
                new DefaultResponseEntity(pMessage, pObject), pHttpStatus);
    }
}
