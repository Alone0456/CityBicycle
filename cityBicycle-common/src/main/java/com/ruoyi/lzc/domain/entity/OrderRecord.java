package com.ruoyi.lzc.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@TableName("order_record")
public class OrderRecord {
 @TableId
  private long orderId;
  private long userId;
  private Long userName;
  private Date payTime;
  private BigDecimal money;
  private Long rentedTimeLong;
  private long rentedId;
  private Integer isPay;


}
