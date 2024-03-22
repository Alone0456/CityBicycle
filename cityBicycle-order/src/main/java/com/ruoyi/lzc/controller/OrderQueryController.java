package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.lzc.common.data.Result;
import com.ruoyi.lzc.common.data.Results;
import com.ruoyi.lzc.domain.entity.OrderRecord;
import com.ruoyi.lzc.common.BeanUtil;
import com.ruoyi.lzc.domain.entity.StationProfile;
import com.ruoyi.lzc.domain.vo.StationProfileVo;
import com.ruoyi.lzc.service.OrderService;
import com.ruoyi.lzc.service.ProfileService;
import com.ruoyi.lzc.service.StationService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @className: OrderController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/13 20:54
 * @version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderQueryController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StationService stationService;
    @PreAuthorize("@ss.hasPermi('order:user')")
     @GetMapping("/my")
     public Result<Page<OrderRecord>> queryMyOrder(
             @RequestParam int page,
             @RequestParam int size
     ){
         Long userId = SecurityUtils.getUserId();
         LambdaQueryWrapper<OrderRecord> orderRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
         orderRecordLambdaQueryWrapper.eq(OrderRecord::getUserId,userId);
         return Results.success(orderService.page(new Page<OrderRecord>(page,size),orderRecordLambdaQueryWrapper));
     }
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/station")
     public Result<List<StationProfileVo>> queryOrderForStation(){
         List<StationProfile> list = profileService.list();
         List<StationProfileVo> convert = BeanUtil.convert(list, StationProfileVo.class);
         convert.forEach(stationProfileVo -> {stationProfileVo.setStationName(stationService.getById(stationProfileVo.getProfileId()).getStationName());});
         return Results.success(convert);
     }
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/all")
     public Result<Page<OrderRecord>> queryAll(
             @RequestParam int page,
             @RequestParam int size
     ){
         return Results.success(orderService.page(new Page<OrderRecord>(page,size)));
     }
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/time")
     public Result<Page<OrderRecord>> getOrderByTime(
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam int page,
            @RequestParam int size
     ){
         LambdaQueryWrapper<OrderRecord> orderRecordLambdaQueryWrapper = new LambdaQueryWrapper<OrderRecord>();
         orderRecordLambdaQueryWrapper.eq(OrderRecord::getIsPay,1).between(OrderRecord::getPayTime,startTime,endTime);
       return  Results.success(orderService.page(new Page<>(page,size),orderRecordLambdaQueryWrapper));

     }

    /**
     * 获取 总收益
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/profile")
     public Result<BigDecimal> getTotalProfile(){
         List<StationProfile> stationProfiles = profileService.list();
         BigDecimal bigDecimal = new BigDecimal(0);
         stationProfiles.forEach(stationProfile ->{bigDecimal.add(stationProfile.getProfile());});
         return Results.success(bigDecimal);
     }

}
