package com.ruoyi.lzc.exception;

/**
 * @className: BicycleAlreadyRentedException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/24 15:56
 * @version: 1.0
 */
public class BicycleAlreadyRentedException extends RuntimeException{
    public BicycleAlreadyRentedException() {
    }

    public BicycleAlreadyRentedException(String message) {
        super(message);
    }

    public BicycleAlreadyRentedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BicycleAlreadyRentedException(Throwable cause) {
        super(cause);
    }

    public BicycleAlreadyRentedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
