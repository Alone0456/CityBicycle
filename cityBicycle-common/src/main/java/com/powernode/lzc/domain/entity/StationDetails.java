package com.powernode.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("station_details")
public class StationDetails {

  private long stationId;
  private String stationName;
  private String site;
  private Integer bicycleNum;
  private Integer rentedNum;
  private Integer returnNum;


}
