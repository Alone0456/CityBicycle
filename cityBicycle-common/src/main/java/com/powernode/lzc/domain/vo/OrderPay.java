package com.powernode.lzc.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: OrderPay
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/15 19:39
 * @version: 1.0
 */
@Data
public class OrderPay {
    private Long rentedId;
    private BigDecimal money;
}
