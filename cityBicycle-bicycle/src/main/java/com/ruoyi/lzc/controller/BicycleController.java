package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lzc.common.BeanUtil;
import com.ruoyi.lzc.domain.entity.Bicycle;
import com.ruoyi.lzc.domain.vo.BicycleReq;
import com.ruoyi.lzc.service.BicycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/bicycle")
public class BicycleController {
    /**
     *
     */

    @Autowired
    private BicycleService bicycleService;
     @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @GetMapping("/all")
     public AjaxResult queryAll(
             @RequestParam("page") int page,
             @RequestParam("size") int size
     ){
         Page<Bicycle> bicyclePage = bicycleService.page(new Page<Bicycle>(page, size));
         return AjaxResult.success(bicyclePage);
     }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @GetMapping("/stationId/{stationId}")
     public AjaxResult queryBicycleByStation(@PathVariable Long stationId,
                                                        @RequestParam int page,
                                                        @RequestParam int size
                                                   ){
         LambdaQueryWrapper<Bicycle> bicycleLambdaQueryWrapper = new LambdaQueryWrapper<>();
         bicycleLambdaQueryWrapper.eq(Bicycle::getStationId,stationId);
         bicycleService.page(new Page<>(page,size),bicycleLambdaQueryWrapper);
        return  AjaxResult.success(bicycleService.page(new Page<Bicycle>(page, size), bicycleLambdaQueryWrapper));
     }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @GetMapping("/status")
      public AjaxResult queryForStatus(@RequestParam("status") Integer status){
         LambdaQueryWrapper<Bicycle> bicycleLambdaQueryWrapper = new LambdaQueryWrapper<>();
         bicycleLambdaQueryWrapper.eq(Bicycle::getBicycleStatus,status);
         return AjaxResult.success(bicycleService.list(bicycleLambdaQueryWrapper));
      }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
    @GetMapping("/{bicycleId}")
    public AjaxResult queryForId(@PathVariable Long bicycleId){
        return AjaxResult.success(bicycleService.getById(bicycleId));
    }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @PostMapping("/save")
    public AjaxResult insert(@RequestBody @Validated BicycleReq bicycle){
        Bicycle bicycle1 = BeanUtil.convert(bicycle, Bicycle.class);
        bicycle1.setCreateTime(new Date());
         return AjaxResult.success(bicycleService.save(bicycle1));
     }

}
