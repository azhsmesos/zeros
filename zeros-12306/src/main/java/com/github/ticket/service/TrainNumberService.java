package com.github.ticket.service;

import java.util.List;

import com.github.ticket.model.TrainNumber;
import com.github.ticket.param.TrainNumberParam;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
public interface TrainNumberService {

    List<TrainNumber> getAllNumber();

    List<TrainNumber> queryNumberInIds(List<Integer> ids);

    boolean save(TrainNumberParam param);

    boolean update(TrainNumberParam param);
}
