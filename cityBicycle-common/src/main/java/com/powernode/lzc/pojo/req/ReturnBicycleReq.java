package com.powernode.lzc.pojo.req;

import lombok.Data;

/**
 * @className: ReturnBicycleReq
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/13 22:12
 * @version: 1.0
 */
@Data
public class ReturnBicycleReq {

    private Long rentedId;
    private Long userId;

}
