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
import com.github.ticket.model.TrainNumber;
import com.github.ticket.model.TrainNumberDetail;
import com.github.ticket.model.TrainStation;
import com.github.ticket.service.TrainNumberDetailService;
import com.github.ticket.service.TrainNumberService;
import com.github.ticket.service.TrainStationService;
import com.github.ticket.view.TrainNumberDetailView;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@RestController
@RequestMapping("/admin/train/numberDetail")
public class TrainNumberDetailController {

    @Autowired
    private TrainNumberDetailService trainNumberDetailService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private TrainNumberService trainNumberService;

    @GetMapping("/list")
    public CommonDataView<List<TrainNumberDetailView>> list() {
        List<TrainNumberDetail> detailList = trainNumberDetailService.getAllNumberDetail();
        List<TrainStation> stationList = trainStationService.getAllStation();
        List<TrainNumber> numberList = trainNumberService.queryNumberInIds(detailList
                .stream()
                .map(TrainNumberDetail::getTrainNumberId)
                .collect(Collectors.toList()));
        Map<Integer, String> stationMap = stationList
                .stream()
                .collect(Collectors.toMap(TrainStation::getId, TrainStation::getName));
        Map<Integer, String> numberMap = numberList
                .stream()
                .collect(Collectors.toMap(TrainNumber::getId, TrainNumber::getName));
        List<TrainNumberDetailView> viewList = detailList
                .stream()
                .map(detail -> TrainNumberDetailView.builder()
                        .id(detail.getId())
                        .fromStationId(detail.getFromStationId())
                        .toStationId(detail.getToStationId())
                        .fromStation(stationMap.get(detail.getFromStationId()))
                        .toStation(stationMap.get(detail.getToStationId()))
                        .fromCityId(detail.getFromCityId())
                        .toCityId(detail.getToCityId())
                        .trainNumberId(detail.getTrainNumberId())
                        .trainNumber(numberMap.get(detail.getTrainNumberId()))
                        .stationIndex(detail.getStationIndex())
                        .relativeMinute(detail.getRelativeMinute())
                        .waitMinute(detail.getWaitMinute())
                        .money(detail.getMoney())
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
