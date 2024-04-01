package com.ruoyi.lzc.exception;

/**
 * @className: BicycleAlreadyRecordException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/4/1 18:36
 * @version: 1.0
 */
public class BicycleAlreadyRecordException extends RuntimeException{
    public BicycleAlreadyRecordException() {
    }

    public BicycleAlreadyRecordException(String message) {
        super(message);
    }

    public BicycleAlreadyRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public BicycleAlreadyRecordException(Throwable cause) {
        super(cause);
    }

    public BicycleAlreadyRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
