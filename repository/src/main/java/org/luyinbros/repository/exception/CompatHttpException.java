package org.luyinbros.repository.exception;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

//TODO check this
public class CompatHttpException extends Exception {
    private int code;
    private String message;

    public CompatHttpException(HttpException e) {
        super(e.getMessage());
        this.code = e.code();
        this.message = e.message();
    }

    public static boolean equals(Throwable e) {
        return e instanceof HttpException ||
                e instanceof CompatHttpException;
    }

    public static CompatHttpException create(Throwable e) {
        if (e instanceof HttpException) {
            return new CompatHttpException((HttpException) e);
        }
        return null;
    }

    public int code() {
        return code;
    }

    /**
     * HTTP status message.
     */
    public String message() {
        return message;
    }
}
