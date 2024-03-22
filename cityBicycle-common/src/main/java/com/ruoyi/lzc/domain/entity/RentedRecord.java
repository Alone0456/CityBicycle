package com.ruoyi.lzc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("rented_record")
public class RentedRecord {
  @TableId
  private long rentedId;
  private long bicycleId;
  private long rentedStationId;
  private long rentedUserId;
  private Date rentedTime;
  private Date returnTime;
  private long returnStationId;

}
