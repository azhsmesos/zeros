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
import com.github.ticket.model.TrainStation;
import com.github.ticket.param.TrainNumberParam;
import com.github.ticket.service.TrainNumberService;
import com.github.ticket.service.TrainStationService;
import com.github.ticket.util.BeanValidator;
import com.github.ticket.util.ErrorCode;
import com.github.ticket.view.TrainNumberView;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@RestController
@RequestMapping("/admin/train/number")
public class TrainNumberController {

    @Autowired
    private TrainNumberService trainNumberService;

    @Autowired
    private TrainStationService trainStationService;

    @GetMapping("/list")
    public CommonDataView<List<TrainNumberView>> list() {
        List<TrainNumber> numberList = trainNumberService.getAllNumber();
        List<TrainStation> stationList = trainStationService.getAllStation();
        Map<Integer, String> stationMap = stationList
                .stream()
                .collect(Collectors.toMap(TrainStation::getId, TrainStation::getName));
        List<TrainNumberView> viewList = numberList
                .stream()
                .map(number -> TrainNumberView.builder()
                        .id(number.getId())
                        .fromStationId(number.getFromStationId())
                        .toStationId(number.getToStationId())
                        .fromStation(stationMap.get(number.getFromStationId()))
                        .toStation(stationMap.get(number.getToStationId()))
                        .fromCityId(number.getFromCityId())
                        .toCityId(number.getToCityId())
                        .trainType(number.getTrainType())
                        .type(number.getType())
                        .seatNum(number.getSeatNum())
                        .build())
                .collect(Collectors.toList());
        return CommonDataView.success(viewList);
    }

    @PostMapping("/save")
    public CommonDataView<TrainCity> save(TrainNumberParam param) {
        BeanValidator.check(param);
        boolean save = trainNumberService.save(param);
        if (!save) {
            return CommonDataView.ofMsg(ErrorCode.SYSTEM_ERROR, "插入失败");
        }
        return CommonDataView.success();
    }

    @PostMapping("/update")
    public CommonDataView<TrainCity> update(TrainNumberParam param) {
        BeanValidator.check(param);
        boolean update = trainNumberService.update(param);
        if (!update) {
            return CommonDataView.ofMsg(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return CommonDataView.success();
    }
}
