package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.lzc.common.BeanUtil;
import com.ruoyi.lzc.common.data.Result;
import com.ruoyi.lzc.common.data.Results;
import com.ruoyi.lzc.domain.entity.*;
import com.ruoyi.lzc.domain.vo.Rented;
import com.ruoyi.lzc.exception.DbOperateUnknownException;
import com.ruoyi.lzc.domain.vo.ReturnBicycle;
import com.ruoyi.lzc.service.*;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('rented:manage')")
    @GetMapping("/query")
    public Result<Page<RentedRecord>> query(
            @RequestParam("orderColumn") String orderColumn,
            @RequestParam("orderType") String orderType,
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        if (orderType.equals("desc")) {
            lambdaQueryWrapper.orderByDesc(orderColumn);
        }else{
            lambdaQueryWrapper.orderByAsc(orderColumn);
        }
        Page<RentedRecord> rentedRecord = rentedService.page(new Page<RentedRecord>(page, size), lambdaQueryWrapper);

        return Results.success(rentedRecord);
    }
    @PreAuthorize("@ss.hasPermi('rented:user')")
    @GetMapping("/user/query")
    public Result<Page<RentedRecord>> queryUser(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<RentedRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RentedRecord::getRentedUserId,userId);
        Page<RentedRecord> rentedRecord = rentedService.page(new Page<RentedRecord>(page, size), lambdaQueryWrapper);

        return Results.success(rentedRecord);
    }
    @PreAuthorize("@ss.hasPermi('rented:service')")
    @PostMapping("/rented")
    public Result<Void> rented(@RequestBody Rented rented){
        Long userId = SecurityUtils.getUserId();
        RentedRecord convert = BeanUtil.convert(rented, RentedRecord.class);
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
        stationDetails.setBicycleNum(stationDetails.getBicycleNum());
        stationService.updateById(stationDetails);
        return  Results.success();
    }
    @PreAuthorize("@ss.hasPermi('rented:service')")
    @PostMapping("/return")
    public Result returnBicycle(@RequestBody ReturnBicycle returnBicycle){
        Long userId = SecurityUtils.getUserId();
        // record
        RentedRecord byId = rentedService.getById(returnBicycle);
        byId.setRentedTime(new Date());
        byId.setReturnStationId(returnBicycle.getStationId());
        rentedService.updateById(byId);
        // order
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setRentedId(returnBicycle.getRentedId());
        long l = byId.getReturnTime().getTime() - byId.getRentedTime().getTime();
        orderRecord.setUserId(userId);
        orderRecord.setRentedTimeLong(l);
        orderRecord.setMoney(orderService.countMoney(l));
        orderRecord.setIsPay(0);
        if (!orderService.save(orderRecord)) {
            throw new DbOperateUnknownException("数据库未知异常");
        }
        // bycicle
        Bicycle byId1 = bicycleService.getById(returnBicycle.getBicycleId());
        byId1.setBicycleStatus(1);
        byId1.setStationId(returnBicycle.getStationId());
        bicycleService.updateById(byId1);
        // station
        StationDetails stationDetails = stationService.getById(returnBicycle.getStationId());
        stationDetails.setBicycleNum(stationDetails.getBicycleNum()+1);
        stationDetails.setReturnNum(stationDetails.getReturnNum()+1);
        stationService.updateById(stationDetails);
        // 向 profile表修改数据
        LambdaQueryWrapper<StationProfile> stationProfileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        stationProfileLambdaQueryWrapper.eq(StationProfile::getStationId,rentedService.getById(byId.getRentedId()).getRentedStationId());
        StationProfile stationProfile = profileService.getOne(stationProfileLambdaQueryWrapper);
        stationProfile.setProfile(stationProfile.getProfile().add(orderRecord.getMoney()));
        profileService.updateById(stationProfile);
        return Results.success();
    }

}
