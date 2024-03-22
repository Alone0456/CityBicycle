package com.ruoyi.lzc.exception;

/**
 * @className: BicycleAlreadyRepairException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 15:18
 * @version: 1.0
 */
public class BicycleAlreadyRepairException extends RuntimeException{
    public BicycleAlreadyRepairException() {
    }

    public BicycleAlreadyRepairException(String message) {
        super(message);
    }

    public BicycleAlreadyRepairException(String message, Throwable cause) {
        super(message, cause);
    }

    public BicycleAlreadyRepairException(Throwable cause) {
        super(cause);
    }

    public BicycleAlreadyRepairException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
