package com.powernode.lzc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powernode.lzc.domain.entity.Bicycle;
import com.powernode.lzc.domain.entity.OrderRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: OrderMapper
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 8:47
 * @version: 1.0
 */
@Mapper
public interface BicycleMapper extends BaseMapper<Bicycle> {

}
