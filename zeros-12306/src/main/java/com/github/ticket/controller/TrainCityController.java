package com.github.ticket.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ticket.model.CommonDataView;
import com.github.ticket.model.TrainCity;
import com.github.ticket.param.TrainCityParam;
import com.github.ticket.service.TrainCityService;
import com.github.ticket.util.BeanValidator;
import com.github.ticket.util.exception.BusinessException;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@RestController
@RequestMapping("/admin/train/city")
public class TrainCityController {

    @Autowired
    private TrainCityService trainCityService;

    @GetMapping("/list")
    public CommonDataView<List<TrainCity>> list() {
        return CommonDataView.success(trainCityService.getAllCity());
    }

    @PostMapping("/save")
    public CommonDataView<TrainCity> save(TrainCityParam param) {
        BeanValidator.check(param);
        boolean insert = trainCityService.save(param);
        if (!insert) {
            throw new BusinessException("插入失败");
        }
        return CommonDataView.success();
    }

    @PostMapping("/update")
    public CommonDataView<TrainCity> update(TrainCityParam param) {
        BeanValidator.check(param);
        boolean update = trainCityService.update(param);
        if (!update) {
            throw new BusinessException("更新失败");
        }
        return CommonDataView.success();
    }
}
