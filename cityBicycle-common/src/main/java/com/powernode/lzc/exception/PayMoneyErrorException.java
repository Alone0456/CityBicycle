package com.powernode.lzc.exception;

/**
 * @className: PayMoneyErrorException
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 19:00
 * @version: 1.0
 */
public class PayMoneyErrorException extends RuntimeException{
    public PayMoneyErrorException() {
    }

    public PayMoneyErrorException(String message) {
        super(message);
    }

    public PayMoneyErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayMoneyErrorException(Throwable cause) {
        super(cause);
    }

    public PayMoneyErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
