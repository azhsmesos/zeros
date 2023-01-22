package com.github.ticket.service;

import java.util.List;

import com.github.ticket.model.TrainStation;
import com.github.ticket.param.TrainStationParam;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
public interface TrainStationService {

    List<TrainStation> getAllStation();

    boolean save(TrainStationParam param);

    boolean update(TrainStationParam param);
}
