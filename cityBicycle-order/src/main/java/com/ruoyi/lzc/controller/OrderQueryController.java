package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.lzc.domain.entity.OrderRecord;
import com.ruoyi.lzc.common.BeanUtil;
import com.ruoyi.lzc.domain.entity.StationProfile;
import com.ruoyi.lzc.domain.vo.StationProfileVo;
import com.ruoyi.lzc.domain.vo.TotalProfile;
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
     public AjaxResult queryMyOrder(
             @RequestParam int page,
             @RequestParam int size
     ){
         Long userId = SecurityUtils.getUserId();
         LambdaQueryWrapper<OrderRecord> orderRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
         orderRecordLambdaQueryWrapper.eq(OrderRecord::getUserId,userId);
         return AjaxResult.success(orderService.page(new Page<OrderRecord>(page,size),orderRecordLambdaQueryWrapper));
     }
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/station")
     public AjaxResult queryOrderForStation(){
         List<StationProfile> list = profileService.list();
         List<StationProfileVo> convert = BeanUtil.convert(list, StationProfileVo.class);
         convert.forEach(stationProfileVo -> {stationProfileVo.setStationName(stationService.getById(stationProfileVo.getProfileId()).getStationName());});
         return AjaxResult.success(convert);
     }
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/all")
     public AjaxResult queryAll(
             @RequestParam int page,
             @RequestParam int size
     ){
         return AjaxResult.success(orderService.page(new Page<OrderRecord>(page,size)));
     }


    /**
     * 获取 总收益
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('order:query')")
     @GetMapping("/profile")
     public AjaxResult getTotalProfile(){
         List<StationProfile> stationProfiles = profileService.list();
//         BigDecimal total = new BigDecimal(0);
        Double total = 0.0;
         for (StationProfile stationProfile: stationProfiles) {
             total+=stationProfile.getProfile().doubleValue();
//             total.add(stationProfile.getProfile());
             System.out.println(stationProfile.getProfile());
         }
         return AjaxResult.success(total);
     }

}
