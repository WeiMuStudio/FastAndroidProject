package org.luyinbros.domain.core;

public class DomainException extends Exception {
    private Exception source;
    private String message;

    public DomainException(String message) {
        super(message);
        this.source = this;
        this.message=message;
    }

    public DomainException(String message, Throwable e) {
        super(e.getMessage());
        this.source = this;
        this.message = message;
    }

    public String message() {
        return message;
    }

    public Exception source() {
        return source;
    }
}
