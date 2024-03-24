package com.ruoyi.lzc.domain.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @className: BicycleReq
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/22 18:22
 * @version: 1.0
 */
@Data
@Validated
public class BicycleReq {
    @NotEmpty
    private String bicycleType;
    @NotEmpty
    private long stationId;
    @NotEmpty
    private String stationName;
}
