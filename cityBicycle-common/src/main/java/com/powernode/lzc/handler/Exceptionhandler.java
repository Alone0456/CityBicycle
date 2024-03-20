package com.powernode.lzc.handler;

import com.powernode.lzc.common.Constant;
import com.powernode.lzc.common.data.Result;
import com.powernode.lzc.common.data.Results;
import com.powernode.lzc.exception.AlreadyPaidException;
import com.powernode.lzc.exception.BicycleAlreadyRepairException;
import com.powernode.lzc.exception.DbOperateUnknownException;
import com.powernode.lzc.exception.PayMoneyErrorException;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Optional;

/**
 * @className: Exceptionhandler
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/15 19:46
 * @version: 1.0
 */
@RestControllerAdvice
public class Exceptionhandler   {

    @ExceptionHandler(value = AlreadyPaidException.class)
    public Result validExceptionHandler(AlreadyPaidException ex) {
        return Results.failure(Constant.ALREADY_PAID,ex.getMessage());
    }

    @ExceptionHandler(value = BicycleAlreadyRepairException.class)
    public Result validExceptionHandler(BicycleAlreadyRepairException ex) {
        return Results.failure(Constant.ALEEADY_REPAIR,ex.getMessage());
    }
    @ExceptionHandler(value = DbOperateUnknownException.class)
    public Result validExceptionHandler(DbOperateUnknownException ex) {
        return Results.failure(Constant.DB_OPEARATE_EXCEPTION,ex.getMessage());
    }
    @ExceptionHandler(value = PayMoneyErrorException.class)
    public Result validExceptionHandler(PayMoneyErrorException ex) {
        return Results.failure(Constant.PAY_MONEY_ERROR,ex.getMessage());
    }
}
