package com.ruoyi.lzc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.lzc.domain.entity.RentedRecord;
import com.ruoyi.lzc.service.RentedService;
import com.ruoyi.lzc.common.Constant;
import com.ruoyi.lzc.mapper.RentedMapper;
import org.springframework.stereotype.Service;

/**
 * @className: OrderServiceimpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 8:53
 * @version: 1.0
 */
@Service
public class RentedServiceImpl extends ServiceImpl<RentedMapper, RentedRecord> implements RentedService {

    @Override
    public Double countMoney(Long l) {
        Long minute = l/(1000*60);
        Long flag = l % (1000*60) == 0 ? 0 : 1L;
        if(minute <= Constant.BLOWE_MINUTE){
            return Constant.BLOWE_MIN_PRICE;
        }
        return (minute+flag)*Constant.PRICE_PRE_MINUTE;
    }
}
