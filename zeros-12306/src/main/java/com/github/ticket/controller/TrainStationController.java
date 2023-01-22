package com.github.ticket.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ticket.model.CommonDataView;
import com.github.ticket.model.TrainCity;
import com.github.ticket.model.TrainStation;
import com.github.ticket.service.TrainCityService;
import com.github.ticket.service.TrainStationService;
import com.github.ticket.view.TrainStationView;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@RestController
@RequestMapping("/admin/train/station")
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private TrainCityService trainCityService;

    @GetMapping("/list")
    public CommonDataView<List<TrainStationView>> list() {
        List<TrainStation> stationList = trainStationService.getAllStation();
        List<TrainCity> cityList = trainCityService.getAllCity();
        Map<Integer, String> cityMap = cityList
                .stream()
                .collect(Collectors.toMap(TrainCity::getId, TrainCity::getName));
        List<TrainStationView> viewList = stationList
                .stream()
                .map(station -> TrainStationView.builder()
                .id(station.getId())
                .name(station.getName())
                .cityId(station.getCityId())
                .cityName(cityMap.get(station.getCityId()))
                .build())
                .collect(Collectors.toList());
        return CommonDataView.success(viewList);
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
