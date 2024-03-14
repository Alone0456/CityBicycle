package com.powernode.lzc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powernode.lzc.domain.entity.StationProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper extends BaseMapper<StationProfile> {
}
