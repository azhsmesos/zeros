package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainNumberDAO;
import com.github.ticket.model.TrainNumber;
import com.github.ticket.service.TrainNumberService;

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
}
