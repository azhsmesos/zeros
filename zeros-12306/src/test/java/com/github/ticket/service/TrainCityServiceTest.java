package com.github.ticket.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.ticket.SpringbootTest;
import com.github.ticket.model.TrainCity;
import com.github.ticket.param.TrainCityParam;
import com.vividsolutions.jts.util.Assert;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */

public class TrainCityServiceTest extends SpringbootTest {


    @Autowired
    private TrainCityService trainCityService;

    @Test
    public void listTest() {
        List<TrainCity> cityList = trainCityService.getAllCity();
        System.out.println(cityList);
    }

    @Test
    public void saveTest() {
        TrainCityParam param = TrainCityParam.builder()
                .name("黔西县")
                .build();
        trainCityService.save(param);
    }

    @Test
    public void updateTest() {
        TrainCityParam param = TrainCityParam.builder()
                .id(1)
                .name("贵阳市")
                .build();
        Assert.isTrue(trainCityService.update(param));
    }
}
