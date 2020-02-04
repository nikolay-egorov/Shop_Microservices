package com.services.accounting.exception;

public class NoEntityException extends Exception {
    private Long id;
    public NoEntityException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
