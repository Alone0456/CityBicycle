package com.powernode.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.powernode.lzc.common.data.Result;
import com.powernode.lzc.common.data.Results;
import com.powernode.lzc.domain.entity.Bicycle;
import com.powernode.lzc.domain.entity.BicycleDamage;
import com.powernode.lzc.domain.vo.RecordDamage;
import com.powernode.lzc.exception.BicycleAlreadyRepairException;
import com.powernode.lzc.exception.DbOperateUnknownException;
import com.powernode.lzc.service.BicycleService;
import com.powernode.lzc.service.DamageService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @className: ImpactController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 14:33
 * @version: 1.0
 */
@RestController
@RequestMapping("/damage")
public class RepairController {

    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private DamageService damageService;
    @GetMapping("/record")
    public Result<Void> record(@RequestParam("bicycleId") Long bicycleId){
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Bicycle> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(Bicycle::getBicycleId,bicycleId);
        Bicycle bicycle = bicycleService.getOne(objectLambdaQueryWrapper);
        bicycle.setBicycleStatus(0);
        bicycleService.updateById(bicycle);
        BicycleDamage bicycleDamage = new BicycleDamage();
        bicycleDamage.setBicycleId(bicycleId);
        bicycleDamage.setRecordTime(new Date());
        bicycleDamage.setStationId(bicycle.getStationId());
        bicycleDamage.setUserId(userId);
        bicycleDamage.setIsMaintent(0);
        if (damageService.save(bicycleDamage)) {
            throw new DbOperateUnknownException("数据库操作未知异常");
        }
        return  Results.success();
    }

    @PostMapping("/repair")
    public Result<Void> repair(@RequestBody RecordDamage recordDamage){
        LambdaQueryWrapper<Bicycle> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(Bicycle::getBicycleId,recordDamage.getBicycleId());
        Bicycle bicycle = bicycleService.getOne(objectLambdaQueryWrapper);
        if(bicycle.getBicycleStatus() == 1){
            throw new BicycleAlreadyRepairException("该车辆已被维修");
        }
        LambdaQueryWrapper<BicycleDamage> bicycleDamageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bicycleDamageLambdaQueryWrapper.eq(BicycleDamage::getBicycleId,recordDamage.getBicycleId());
        BicycleDamage one = damageService.getOne(bicycleDamageLambdaQueryWrapper);
        one.setUpdateTime(new Date());
        one.setIsMaintent(1);
        damageService.updateById(one);
        bicycle.setBicycleStatus(1);
        bicycle.setInspectTime(new Date());
        bicycle.setInspectNum(bicycle.getInspectNum()+1);
        bicycleService.updateById(bicycle);
        return Results.success();
    }
}
