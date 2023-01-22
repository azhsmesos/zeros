package com.github.ticket.service;

import java.util.List;

import com.github.ticket.model.TrainCity;
import com.github.ticket.param.TrainCityParam;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
public interface TrainCityService {

    List<TrainCity> getAllCity();

    boolean save(TrainCityParam param);

    boolean update(TrainCityParam param);
}
