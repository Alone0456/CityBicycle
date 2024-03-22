package com.ruoyi.lzc.domain.entity;

import lombok.Data;

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
