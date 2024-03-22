package com.ruoyi.lzc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @className: Mybatisconfig
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/21 22:13
 * @version: 1.0
 */
@Configuration
@MapperScan("com.ruoyi.**.mapper")
public class Mybatisconfig {

}
