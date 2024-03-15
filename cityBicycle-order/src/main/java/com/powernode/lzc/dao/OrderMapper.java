package com.powernode.lzc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powernode.lzc.domain.entity.OrderRecord;
import com.powernode.lzc.domain.entity.OrderStation;
import com.powernode.lzc.domain.entity.StationOrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @className: OrderMapper
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 8:47
 * @version: 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderRecord> {


}
