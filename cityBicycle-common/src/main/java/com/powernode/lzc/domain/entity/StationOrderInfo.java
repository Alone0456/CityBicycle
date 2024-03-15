package com.powernode.lzc.domain.entity;

import com.powernode.lzc.service.OrderService;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @className: StationOrderInfo
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 19:17
 * @version: 1.0
 */
@Data
public class StationOrderInfo {
    private Long stationId;
    private String stationName;
    private List<OrderRecord> orders;
    private Long rentedId;
}
