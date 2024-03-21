package com.powernode.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.powernode.lzc.domain.entity.OrderRecord;
import com.powernode.lzc.domain.entity.StationProfile;
import com.powernode.lzc.domain.vo.OrderPay;
import com.powernode.lzc.exception.AlreadyPaidException;
import com.powernode.lzc.exception.DbOperateUnknownException;
import com.powernode.lzc.exception.PayMoneyErrorException;
import com.powernode.lzc.service.OrderService;
import com.powernode.lzc.common.data.Result;
import com.powernode.lzc.common.data.Results;
import com.powernode.lzc.service.ProfileService;
import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public Result<Void> pay(@RequestBody OrderPay orderPay){
        OrderRecord byId = orderService.getById(orderPay.getRentedId());
        if(byId.getIsPay() == 1){
            throw new AlreadyPaidException("您已经支付过该订单了");
        }
        if(!orderPay.getMoney().equals(byId.getMoney())){
            throw new PayMoneyErrorException("支付金额不一致");
        }
        byId.setIsPay(1);
        byId.setPayTime(new Date());
        boolean flag = orderService.updateById(byId);
        if(!flag){
           throw new DbOperateUnknownException("数据库操作未知异常");
        }
       return  Results.success();
    }
}
