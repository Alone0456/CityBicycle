package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ruoyi.lzc.common.data.Result;
import com.ruoyi.lzc.common.data.Results;
import com.ruoyi.lzc.domain.entity.Bicycle;
import com.ruoyi.lzc.service.BicycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
     public Result<Page<Bicycle>> queryAll(
             @RequestParam("page") int page,
             @RequestParam("size") int size
     ){
           return Results.success(bicycleService.page(new Page<Bicycle>(page,size)));
     }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @GetMapping("/stationId/{stationId}")
     public Result<Page<Bicycle>> queryBicycleByStation(@PathVariable Long stationId,
                                                        @RequestParam int page,
                                                        @RequestParam int size
                                                   ){
         LambdaQueryWrapper<Bicycle> bicycleLambdaQueryWrapper = new LambdaQueryWrapper<>();
         bicycleLambdaQueryWrapper.eq(Bicycle::getStationId,stationId);
         bicycleService.page(new Page<>(page,size),bicycleLambdaQueryWrapper);
        return  Results.success(bicycleService.page(new Page<Bicycle>(page, size), bicycleLambdaQueryWrapper));
     }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @GetMapping("/status")
      public Result<List<Bicycle>> queryForStatus(@RequestParam("status") Integer status){
         LambdaQueryWrapper<Bicycle> bicycleLambdaQueryWrapper = new LambdaQueryWrapper<>();
         bicycleLambdaQueryWrapper.eq(Bicycle::getBicycleStatus,status);
         return Results.success(bicycleService.list(bicycleLambdaQueryWrapper));
      }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
    @GetMapping("/{bicycleId}")
    public Result<Bicycle> queryForId(@PathVariable Long bicycleId){
        return Results.success(bicycleService.getById(bicycleId));
    }
    @PreAuthorize("@ss.hasPermi('bicycle:manage')")
     @PostMapping("/save")
    public Result insert(@RequestBody Bicycle bicycle){
         bicycle.setCreateTime(new Date());
         return Results.success(bicycleService.save(bicycle));
     }

}
