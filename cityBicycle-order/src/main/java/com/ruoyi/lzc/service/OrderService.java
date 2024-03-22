package com.ruoyi.lzc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.lzc.domain.entity.OrderRecord;

import java.math.BigDecimal;


public interface OrderService extends IService<OrderRecord> {



    public BigDecimal countMoney(Long l);


}
