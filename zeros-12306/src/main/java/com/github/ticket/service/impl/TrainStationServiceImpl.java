package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainStationDAO;
import com.github.ticket.model.TrainStation;
import com.github.ticket.service.TrainStationService;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
public class TrainStationServiceImpl implements TrainStationService {

    @Autowired
    private TrainStationDAO trainStationDAO;

    public List<TrainStation> getAllStation() {
        return trainStationDAO.getAllStation();
    }
}
