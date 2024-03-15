package com.powernode.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bicycle_damage")
public class BicycleDamage {
  @TableId
  private long damageId;
  private Date recordTime;
  private long bicycleId;
  private long stationId;
  private String stationName;
  private String details;
  private long userId;
  private long isMaintent;
  private Date updateTime;


}
