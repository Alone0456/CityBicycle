package com.powernode.lzc.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Rented
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/13 21:31
 * @version: 1.0
 */
@Data
@Builder
public class Rented implements Serializable {

    private Long rentedId;
    private Long bicycleId;
    private long rentedStationId;
    private long rentedUserId;


}
