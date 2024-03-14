package com.powernode.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.lzc.common.BeanUtil;
import com.powernode.lzc.common.data.Result;
import com.powernode.lzc.common.data.Results;
import com.powernode.lzc.domain.entity.Bicycle;
import com.powernode.lzc.domain.entity.OrderRecord;
import com.powernode.lzc.domain.entity.RentedRecord;
import com.powernode.lzc.domain.entity.StationDetails;
import com.powernode.lzc.domain.vo.Rented;
import com.powernode.lzc.domain.vo.Return;
import com.powernode.lzc.exception.DbOperateUnknownException;
import com.powernode.lzc.pojo.req.ReturnBicycleReq;
import com.powernode.lzc.service.BicycleService;
import com.powernode.lzc.service.OrderService;
import com.powernode.lzc.service.RentedService;
import com.powernode.lzc.service.StationService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/rented")
public class RentedController {

    @Autowired
    private RentedService rentedService;
    @Autowired
    private StationService stationService;
    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private OrderService orderService;

    /**
     * 租车
     * 还车
     * 查询本人的租借记录
     * 用户查询未归还车辆
     *
     * @return
     */
    @GetMapping("/service")
    public Result<Page<StationDetails>> query(
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
        Page<StationDetails> stationPage = stationService.page(new Page<StationDetails>(page, size), lambdaQueryWrapper);

        return Results.success(stationPage);
    }

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

    @PostMapping("/return")
    public Result returnBicycle(@RequestBody Return returnBicycle){
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
        return Results.success();
    }

}
