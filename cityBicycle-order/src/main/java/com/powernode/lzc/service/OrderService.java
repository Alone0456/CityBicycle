package com.powernode.lzc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.powernode.lzc.domain.entity.OrderRecord;
import com.powernode.lzc.domain.vo.StationProfileVo;

import java.math.BigDecimal;
import java.util.List;


public interface OrderService extends IService<OrderRecord> {



    public BigDecimal countMoney(Long l);


}
