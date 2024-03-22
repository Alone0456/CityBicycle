package com.ruoyi.lzc.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ruoyi.lzc.domain.entity.RentedRecord;


public interface RentedService extends IService<RentedRecord> {

    /**
     * 计算价钱
     *
     * @return
     */
    public Double countMoney(Long l);

}
