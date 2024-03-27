package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lzc.common.BeanUtil;
import com.ruoyi.lzc.domain.entity.*;
import com.ruoyi.lzc.domain.vo.Rented;
import com.ruoyi.lzc.exception.BicycleAlreadyRentedException;
import com.ruoyi.lzc.exception.BicycleAlreadyReturnException;
import com.ruoyi.lzc.exception.DbOperateUnknownException;
import com.ruoyi.lzc.domain.vo.ReturnBicycle;
import com.ruoyi.lzc.service.*;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @className: StationController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 13:56
 * @version: 1.0
 */
@RestController
@RequestMapping("/service")
public class RentedController {

    @Autowired
    private RentedService rentedService;
    @Autowired
    private StationService stationService;
    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProfileService profileService;

    /**
     * 租车
     * 还车
     * 查询本人的租借记录
     * 用户查询未归还车辆
     * 对租借记录进行查询
     *
     * @return
     */


    @PreAuthorize("@ss.hasPermi('rented:manage')")
    @PostMapping("/query")
    public AjaxResult query(@RequestBody Map<String,Object> params
           ){
        QueryWrapper<RentedRecord> rentedRecordQueryWrapper = new QueryWrapper<>();
        Set<String> strings = params.keySet();
        for (String param:strings){
            if(param.equals("page") || param.equals("size")){
                break;
            }
            rentedRecordQueryWrapper.eq(param,params.get(param));
        }
        Page<RentedRecord> rentedRecord = rentedService.page(new Page<RentedRecord>(Long.valueOf(params.get("page").toString()),Long.valueOf(params.get("size").toString())),rentedRecordQueryWrapper);
        return AjaxResult.success(rentedRecord);
    }
    @PreAuthorize("@ss.hasPermi('rented:user')")
    @GetMapping("/user/query")
    public AjaxResult queryUser(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<RentedRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RentedRecord::getRentedUserId,userId);
        Page<RentedRecord> rentedRecord = rentedService.page(new Page<RentedRecord>(page, size), lambdaQueryWrapper);

        return AjaxResult.success(rentedRecord);
    }
    @PreAuthorize("@ss.hasPermi('rented:service')")
    @PostMapping("/rented")
    public AjaxResult rented(@RequestBody Rented rented){
        LambdaQueryWrapper<Bicycle> bicycleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bicycleLambdaQueryWrapper.eq(Bicycle::getBicycleId,rented.getBicycleId()).select(Bicycle::getBicycleStatus);
        Bicycle one = bicycleService.getOne(bicycleLambdaQueryWrapper);
        if(one.getBicycleStatus() != 0){
            throw  new BicycleAlreadyRentedException("该车辆已被借出");
        }
        Long userId = SecurityUtils.getUserId();
        RentedRecord convert = BeanUtil.convert(rented, RentedRecord.class);
        convert.setRentedTime(new Date());
        convert.setRentedUserId(userId);
        if (!rentedService.save(convert)) {
            throw new DbOperateUnknownException("数据库操作未知异常");
        }
        Bicycle byId = bicycleService.getById(rented.getBicycleId());
        byId.setRentNum(byId.getRentNum()+1);
        byId.setBicycleStatus(0);
        bicycleService.updateById(byId);
        StationDetails stationDetails = stationService.getById(byId.getStationId());
        stationDetails.setRentedNum(stationDetails.getRentedNum()+1);
        stationDetails.setBicycleNum(stationDetails.getBicycleNum()-1);
        stationService.updateById(stationDetails);
        return  AjaxResult.success();
    }
    @PreAuthorize("@ss.hasPermi('rented:service')")
    @PostMapping("/return")
    public AjaxResult returnBicycle(@RequestBody ReturnBicycle returnBicycle){

        Long userId = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        // bycicle
        Bicycle byId1 = bicycleService.getById(returnBicycle.getBicycleId());
        byId1.setBicycleStatus(1);
        byId1.setStationId(returnBicycle.getStationId());
        bicycleService.updateById(byId1);
        if(byId1.getBicycleStatus() == 1){
            throw new BicycleAlreadyReturnException("当前车辆已归还");
        }
        // record
        RentedRecord byId = rentedService.getById(returnBicycle.getRentedId());
        byId.setReturnTime(new Date());
        byId.setReturnStationId(returnBicycle.getStationId());
        rentedService.updateById(byId);
        // order
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setRentedId(returnBicycle.getRentedId());
        long l = byId.getReturnTime().getTime() - byId.getRentedTime().getTime();
        orderRecord.setUserId(userId);
        orderRecord.setUserName(username);
        orderRecord.setRentedTimeLong(l);
        orderRecord.setMoney(orderService.countMoney(l));
        orderRecord.setIsPay(0);
        if (!orderService.save(orderRecord)) {
            throw new DbOperateUnknownException("数据库未知异常");
        }

        // station
        StationDetails stationDetails = stationService.getById(returnBicycle.getStationId());
        stationDetails.setBicycleNum(stationDetails.getBicycleNum()+1);
        stationDetails.setReturnNum(stationDetails.getReturnNum()+1);
        stationService.updateById(stationDetails);
        // 向 profile表修改数据
        LambdaQueryWrapper<StationProfile> stationProfileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        long rentedStationId = rentedService.getById(byId.getRentedId()).getRentedStationId();
        stationProfileLambdaQueryWrapper.eq(StationProfile::getStationId,rentedStationId);
        StationProfile stationProfile = profileService.getOne(stationProfileLambdaQueryWrapper);
        stationProfile.setProfile(stationProfile.getProfile().add(orderRecord.getMoney()));
        profileService.updateById(stationProfile);
        return AjaxResult.success();
    }

}
