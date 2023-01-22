package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainCityDAO;
import com.github.ticket.model.TrainCity;
import com.github.ticket.param.TrainCityParam;
import com.github.ticket.service.TrainCityService;
import com.github.ticket.util.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
@Slf4j
public class TrainCityServiceImpl implements TrainCityService {

    @Autowired
    private TrainCityDAO trainCityDAO;

    @Override
    public List<TrainCity> getAllCity() {
        return trainCityDAO.getAllCity();
    }

    @Override
    public boolean save(TrainCityParam param) {
        TrainCity city = TrainCity.builder()
                .name(param.getName())
                .build();
        if (checkExist(param.getName(), param.getId())) {
            throw new BusinessException("存在相同名称的城市");
        }
        return trainCityDAO.insert(city);
    }

    @Override
    public boolean update(TrainCityParam param) {
        if (checkExist(param.getName(), param.getId())) {
            throw new BusinessException("存在相同名称的城市");
        }
        TrainCity before = trainCityDAO.selectByPrimaryKey(param.getId());
        if (before == null) {
            throw new BusinessException("该城市并不存在");
        }
        TrainCity city = TrainCity.builder()
                .id(param.getId())
                .name(param.getName())
                .build();
        return trainCityDAO.update(city);
    }

    private boolean checkExist(String name, Integer cityId) {
        return trainCityDAO.countByNameAndId(name, cityId) > 0;
    }
}
