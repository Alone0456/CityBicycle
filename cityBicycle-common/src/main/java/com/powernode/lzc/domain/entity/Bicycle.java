package com.powernode.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("t_bicycle")
public class Bicycle {
  @TableId
  private long bicycleId;
  private String bicycleType;
  private long rentNum;
  private Date createTime;
  private long bicycleStatus;
  private long stationId;
  private String stationName;
  private Date inspectTime;
  private long inspectNum;
}
