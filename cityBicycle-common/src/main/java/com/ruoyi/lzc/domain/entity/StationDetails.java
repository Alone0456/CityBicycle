package com.ruoyi.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("station_details")
public class StationDetails {
 @TableId
  private long stationId;
  private String stationName;
  private String site;
  private Integer bicycleNum;
  private Integer rentedNum;
  private Integer returnNum;


}
