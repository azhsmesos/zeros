package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainCityDAO;
import com.github.ticket.dao.baseDAO.TrainStationDAO;
import com.github.ticket.model.TrainCity;
import com.github.ticket.model.TrainStation;
import com.github.ticket.param.TrainStationParam;
import com.github.ticket.service.TrainStationService;
import com.github.ticket.util.exception.BusinessException;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
public class TrainStationServiceImpl implements TrainStationService {

    @Autowired
    private TrainStationDAO trainStationDAO;
    @Autowired
    private TrainCityDAO trainCityDAO;

    public List<TrainStation> getAllStation() {
        return trainStationDAO.getAllStation();
    }

    @Override
    public boolean save(TrainStationParam param) {
        TrainCity city = trainCityDAO.selectByPrimaryKey(param.getCityId());
        if (city == null) {
            throw new BusinessException("站点所属城市不存在");
        }
        if (checkExist(param.getName(), param.getId(), param.getCityId())) {
            throw new BusinessException("该城市下存在相同名称站点");
        }
        TrainStation station = TrainStation.builder()
                .name(param.getName())
                .id(param.getId())
                .cityId(param.getCityId())
                .build();

        return trainStationDAO.insert(station);
    }

    @Override
    public boolean update(TrainStationParam param) {
        TrainCity city = trainCityDAO.selectByPrimaryKey(param.getCityId());
        if (city == null) {
            throw new BusinessException("站点所属城市不存在");
        }
        if (checkExist(param.getName(), param.getId(), param.getCityId())) {
            throw new BusinessException("该城市下存在相同名称站点");
        }
        TrainStation station = trainStationDAO.selectByPrimaryKey(param.getId());
        if (station == null) {
            throw new BusinessException("待更新站点不存在");
        }
        TrainStation updateStation = TrainStation.builder()
                .name(param.getName())
                .cityId(param.getCityId())
                .id(param.getId())
                .build();
        return trainStationDAO.update(station);
    }

    private boolean checkExist(String name, Integer stationId, Integer cityId) {
        return trainStationDAO.countByIdAndNameAndCityId(name, stationId, cityId) > 0;
    }
}
