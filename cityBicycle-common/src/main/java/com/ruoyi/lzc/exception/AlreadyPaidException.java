package com.ruoyi.lzc.exception;

/**
 * @className: AlreadyPaidException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 10:35
 * @version: 1.0
 */
public class AlreadyPaidException extends RuntimeException{
    public AlreadyPaidException() {
        super();
    }

    public AlreadyPaidException(String message) {
        super(message);
    }

    public AlreadyPaidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyPaidException(Throwable cause) {
        super(cause);
    }

    protected AlreadyPaidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
