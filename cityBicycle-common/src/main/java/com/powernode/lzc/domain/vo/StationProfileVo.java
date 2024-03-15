package com.powernode.lzc.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: StationProfile
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 19:09
 * @version: 1.0
 */
@Data
public class StationProfileVo {

    private Long profileId;
    private Long stationId;
    private String stationName;
    private BigDecimal profile;
}
