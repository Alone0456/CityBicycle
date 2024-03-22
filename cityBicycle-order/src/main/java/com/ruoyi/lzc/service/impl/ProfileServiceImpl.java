package com.ruoyi.lzc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.lzc.mapper.ProfileMapper;
import com.ruoyi.lzc.domain.entity.StationProfile;
import com.ruoyi.lzc.service.ProfileService;
import org.springframework.stereotype.Service;

/**
 * @className: ProfileServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/14 20:18
 * @version: 1.0
 */
@Service
public class ProfileServiceImpl extends ServiceImpl<ProfileMapper, StationProfile> implements ProfileService {
}
