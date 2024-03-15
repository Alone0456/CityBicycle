package com.powernode.lzc.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: Return
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 17:06
 * @version: 1.0
 */
@Data
public class ReturnBicycle implements Serializable {

    private Long bicycleId;
    private Long rentedId;
    private Long stationId;

}
