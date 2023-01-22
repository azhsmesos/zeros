package com.github.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ticket.dao.baseDAO.TrainNumberDetailDAO;
import com.github.ticket.model.TrainNumberDetail;
import com.github.ticket.service.TrainNumberDetailService;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Service
public class TrainNumberDetailServiceImpl implements TrainNumberDetailService {

    @Autowired
    private TrainNumberDetailDAO trainNumberDetailDAO;

    @Override
    public List<TrainNumberDetail> getAllNumberDetail() {
        return trainNumberDetailDAO.getAllNumberDetail();
    }
}
