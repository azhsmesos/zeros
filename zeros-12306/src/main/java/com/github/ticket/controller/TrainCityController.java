package com.github.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ticket.model.CommonDataView;
import com.github.ticket.model.TrainCity;
import com.github.ticket.service.TrainCityService;

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
    public CommonDataView<TrainCity> save() {
        return CommonDataView.success();
    }

    @PostMapping("/update")
    public CommonDataView<TrainCity> update() {
        return CommonDataView.success();
    }
}
