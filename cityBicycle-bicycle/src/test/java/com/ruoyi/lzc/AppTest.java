package com.ruoyi.lzc;


import com.ruoyi.lzc.domain.entity.Bicycle;
import com.ruoyi.lzc.service.BicycleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest
{
    @Autowired
    BicycleService bicycleService;
    @Test
    public void test(){
        Bicycle byId = bicycleService.getById(1);
    }
}
