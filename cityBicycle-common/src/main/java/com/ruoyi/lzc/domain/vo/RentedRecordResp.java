package com.ruoyi.lzc.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @className: RentedRecordResp
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/28 14:09
 * @version: 1.0
 */
@Data
public class RentedRecordResp {
    private long rentedId;
    private long bicycleId;
    private long rentedStationId;
    private String rentedStationName;
    private long rentedUserId;
    private Date rentedTime;
    private Date returnTime;
    private long returnStationId;
    private String returnStationName;
}
