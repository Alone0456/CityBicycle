package com.ruoyi.lzc.exception;

/**
 * @className: BicycleAlreadyReturnException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/24 16:16
 * @version: 1.0
 */
public class BicycleAlreadyReturnException extends RuntimeException{
    public BicycleAlreadyReturnException() {
    }

    public BicycleAlreadyReturnException(String message) {
        super(message);
    }

    public BicycleAlreadyReturnException(String message, Throwable cause) {
        super(message, cause);
    }

    public BicycleAlreadyReturnException(Throwable cause) {
        super(cause);
    }

    public BicycleAlreadyReturnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
