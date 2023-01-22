package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainCityDAO;
import com.github.ticket.model.TrainCity;
import com.github.ticket.service.TrainCityService;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
public class TrainCityServiceImpl implements TrainCityService {

    @Autowired
    private TrainCityDAO trainCityDAO;

    @Override
    public List<TrainCity> getAllCity() {
        return trainCityDAO.getAllCity();
    }
}
