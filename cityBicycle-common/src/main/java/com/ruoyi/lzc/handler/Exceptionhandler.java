package com.ruoyi.lzc.handler;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lzc.common.Constant;

import com.ruoyi.lzc.exception.*;
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
    public AjaxResult validExceptionHandler(AlreadyPaidException ex) {
        return AjaxResult.error(Constant.ALREADY_PAID,ex.getMessage());
    }

    @ExceptionHandler(value = BicycleAlreadyRepairException.class)
    public AjaxResult validExceptionHandler(BicycleAlreadyRepairException ex) {
        return AjaxResult.error(Constant.ALEEADY_REPAIR,ex.getMessage());
    }
    @ExceptionHandler(value = DbOperateUnknownException.class)
    public AjaxResult validExceptionHandler(DbOperateUnknownException ex) {
        return AjaxResult.error(Constant.DB_OPEARATE_EXCEPTION,ex.getMessage());
    }
    @ExceptionHandler(value = PayMoneyErrorException.class)
    public AjaxResult validExceptionHandler(PayMoneyErrorException ex) {
        return AjaxResult.error(Constant.PAY_MONEY_ERROR,ex.getMessage());
    }
    @ExceptionHandler(value = BicycleAlreadyRentedException.class)
    public AjaxResult validExceptionHandler(BicycleAlreadyRentedException ex) {
        return AjaxResult.error(Constant.BICYCLE_ALREADY_RENTED,ex.getMessage());
    }
    @ExceptionHandler(value = BicycleAlreadyReturnException.class)
    public AjaxResult validExceptionHandler(BicycleAlreadyReturnException ex) {
        return AjaxResult.error(Constant.BICYCLE_ALREADY_RETURN,ex.getMessage());
    }
    @ExceptionHandler(value = BicycleAlreadyRecordException.class)
    public AjaxResult validExceptionHandler(BicycleAlreadyRecordException ex) {
        return AjaxResult.error(Constant.BICYCLE_ALREADY_RETURN,ex.getMessage());
    }
}
