package com.ruoyi.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("station_profile")
public class StationProfile {
    @TableId
    private Long profileId;
    private Long stationId;
    private BigDecimal profile;
}
