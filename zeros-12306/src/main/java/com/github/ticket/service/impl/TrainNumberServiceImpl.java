package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.common.TrainType;
import com.github.ticket.dao.baseDAO.TrainNumberDAO;
import com.github.ticket.model.TrainNumber;
import com.github.ticket.param.TrainNumberParam;
import com.github.ticket.service.TrainNumberService;
import com.github.ticket.util.exception.BusinessException;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
public class TrainNumberServiceImpl implements TrainNumberService {

    @Autowired
    private TrainNumberDAO trainNumberDAO;

    @Override
    public List<TrainNumber> getAllNumber() {
        return trainNumberDAO.getAllNumber();
    }

    @Override
    public List<TrainNumber> queryNumberInIds(List<Integer> ids) {
        return trainNumberDAO.queryNumberInIds(ids);
    }

    @Override
    public boolean save(TrainNumberParam param) {
        TrainNumber number = trainNumberDAO.findTrainName(param.getName());
        if (number != null) {
            throw new BusinessException("当前车次已经存在");
        }
        TrainNumber newNumber = TrainNumber.builder()
                .name(param.getName())
                .trainType(param.getTrainType())
                .type(param.getType().shortValue())
                .seatNum(TrainType.valueOf(param.getTrainType()).getCount())
                .build();

        return trainNumberDAO.insert(newNumber);
    }

    @Override
    public boolean update(TrainNumberParam param) {
        TrainNumber number = trainNumberDAO.findTrainName(param.getName());
        if (number != null && number.getId().intValue() != param.getId().intValue()) {
            throw new BusinessException("当前车次已经存在");
        }
        TrainNumber newNumber = TrainNumber.builder()
                .id(param.getId())
                .name(param.getName())
                .trainType(param.getTrainType())
                .type(param.getType().shortValue())
                .seatNum(TrainType.valueOf(param.getTrainType()).getCount())
                .build();

        return trainNumberDAO.update(number);
    }
}
