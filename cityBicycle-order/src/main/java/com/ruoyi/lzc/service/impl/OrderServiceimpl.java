package com.ruoyi.lzc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.lzc.domain.entity.OrderRecord;
import com.ruoyi.lzc.common.Constant;
import com.ruoyi.lzc.mapper.OrderMapper;
import com.ruoyi.lzc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @className: OrderServiceimpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 8:53
 * @version: 1.0
 */
@Service
public class OrderServiceimpl extends ServiceImpl<OrderMapper, OrderRecord> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public BigDecimal countMoney(Long l) {
        Long minute = l/(1000*60);
        Long flag = l % (1000*60) == 0 ? 0 : 1L;
        // 判断骑行时间
        if(minute <= Constant.BLOWE_MINUTE){
            return BigDecimal.valueOf(Constant.BLOWE_MIN_PRICE);
        }
        return BigDecimal.valueOf((minute+flag)* Constant.PRICE_PRE_MINUTE);
    }
}

