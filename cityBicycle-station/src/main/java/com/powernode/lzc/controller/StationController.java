package com.powernode.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.lzc.common.data.Result;
import com.powernode.lzc.common.data.Results;
import com.powernode.lzc.domain.entity.StationDetails;
import com.powernode.lzc.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;

/**
 * @className: StationController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 13:56
 * @version: 1.0
 */
@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * 查询所有
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('station:manage')")
    @GetMapping("/all")
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
    @PreAuthorize("@ss.hasPermi('station:user')")
    @GetMapping("/user/all")
    public Result<Page<StationDetails>> queryForUser(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        Page<StationDetails> stationPage = stationService.page(new Page<StationDetails>(page, size), lambdaQueryWrapper);

        return Results.success(stationPage);
    }
    @PreAuthorize("@ss.hasPermi('station:manage')")
    @GetMapping("/{stationId}")
    public Result queryByStationId(@PathVariable Long stationId){
        return Results.success(stationService.getById(stationId));
    }

}
