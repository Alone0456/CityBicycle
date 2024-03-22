package com.ruoyi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @className: MybatisConfig
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/21 22:00
 * @version: 1.0
 */
@Configuration
@MapperScan("com.ruoyi.lzc.mapper")
public class MybatisConfig {
}
