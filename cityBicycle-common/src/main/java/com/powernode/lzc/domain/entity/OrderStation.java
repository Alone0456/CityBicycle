package com.powernode.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: OrderStation
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 9:16
 * @version: 1.0
 */
@Data
@TableName("order_record")
public class OrderStation {

    private long orderId;
    private long userId;
    private Long userName;
    private Date payTime;
    private Long stationId;
    private BigDecimal money;
    private Date rentedTime;
    private long rentedId;
    private long isPay;


}
