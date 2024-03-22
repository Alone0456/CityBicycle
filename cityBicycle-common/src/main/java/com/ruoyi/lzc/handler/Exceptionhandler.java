package com.ruoyi.lzc.handler;

import com.ruoyi.lzc.common.Constant;
import com.ruoyi.lzc.common.data.Result;
import com.ruoyi.lzc.common.data.Results;
import com.ruoyi.lzc.exception.AlreadyPaidException;
import com.ruoyi.lzc.exception.BicycleAlreadyRepairException;
import com.ruoyi.lzc.exception.DbOperateUnknownException;
import com.ruoyi.lzc.exception.PayMoneyErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
