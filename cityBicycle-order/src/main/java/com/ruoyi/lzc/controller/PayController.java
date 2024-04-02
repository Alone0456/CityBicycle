package com.ruoyi.lzc.controller;

import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.lzc.domain.entity.OrderRecord;
import com.ruoyi.lzc.domain.vo.OrderPay;
import com.ruoyi.lzc.exception.AlreadyPaidException;
import com.ruoyi.lzc.exception.DbOperateUnknownException;
import com.ruoyi.lzc.exception.PayMoneyErrorException;
import com.ruoyi.lzc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @className: PayController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 9:41
 * @version: 1.0
 */
@RestController
@RequestMapping("/order")
public class PayController {

    @Autowired
    private OrderService orderService;
    @PreAuthorize("@ss.hasPermi('order:pay')")
    @PostMapping("/pay")
    @Transactional
    public AjaxResult pay(@RequestBody OrderPay orderPay){
        OrderRecord byId = orderService.getById(orderPay.getRentedId());
        if(byId.getIsPay() == 1){
            throw new AlreadyPaidException("您已经支付过该订单了");
        }
        if(orderPay.getMoney().compareTo(byId.getMoney()) != 0){
            throw new PayMoneyErrorException("支付金额不一致，请核对订单信息");
        }
        byId.setIsPay(1);
        byId.setPayTime(new Date());
        boolean flag = orderService.updateById(byId);
        if(!flag){
           throw new DbOperateUnknownException("数据库操作未知异常,请重试");
        }
       return  AjaxResult.success();
    }
}
