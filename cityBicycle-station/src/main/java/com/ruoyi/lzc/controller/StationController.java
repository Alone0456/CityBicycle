package com.ruoyi.lzc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lzc.domain.entity.StationDetails;
import com.ruoyi.lzc.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public AjaxResult query(
            @RequestParam("orderColumn") String orderColumn,
            @RequestParam("orderType") String orderType,
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){

        QueryWrapper<StationDetails> stationDetailsQueryWrapper = new QueryWrapper<>();
        if (orderType.equals("desc")) {
            stationDetailsQueryWrapper.orderByDesc(orderColumn);
        }else{
            stationDetailsQueryWrapper.orderByAsc(orderColumn);
        }
        Page<StationDetails> stationPage = stationService.page(new Page<StationDetails>(page, size), stationDetailsQueryWrapper);

        return AjaxResult.success(stationPage);
    }
    @PreAuthorize("@ss.hasPermi('station:user')")
    @GetMapping("/user/all")
    public AjaxResult queryForUser(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page
    ){

        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        Page<StationDetails> stationPage = stationService.page(new Page<StationDetails>(page, size), lambdaQueryWrapper);

        return AjaxResult.success(stationPage);
    }
    @PreAuthorize("@ss.hasPermi('station:manage')")
    @GetMapping("/{stationId}")
    public AjaxResult queryByStationId(@PathVariable Long stationId){
        return AjaxResult.success(stationService.getById(stationId));
    }

}
