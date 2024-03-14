package com.powernode.lzc.exception;

/**
 * @className: DbOperateUnknownException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 10:39
 * @version: 1.0
 */
public class DbOperateUnknownException extends RuntimeException{
    public DbOperateUnknownException() {
    }

    public DbOperateUnknownException(String message) {
        super(message);
    }

    public DbOperateUnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbOperateUnknownException(Throwable cause) {
        super(cause);
    }

    public DbOperateUnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
