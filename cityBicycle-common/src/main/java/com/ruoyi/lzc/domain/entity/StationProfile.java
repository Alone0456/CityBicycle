package com.ruoyi.lzc.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: StationProfile
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 20:14
 * @version: 1.0
 */
@Data
public class StationProfile {
    private Long profileId;
    private Long stationId;
    private BigDecimal profile;
}
