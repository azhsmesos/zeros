package com.github.ticket.dao.baseDAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.ticket.model.TrainStation;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Repository
public class TrainStationDAO {

    @Resource(name = "masterDBTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<TrainStation> getAllStation() {
        String sql = "select * from train_station";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TrainStation.class));
    }
}
