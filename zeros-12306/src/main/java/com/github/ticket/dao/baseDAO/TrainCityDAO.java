package com.github.ticket.dao.baseDAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.ticket.model.TrainCity;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Repository
public class TrainCityDAO {

    @Resource(name = "masterDBTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<TrainCity> getAllCity() {
        String sql = "select * from train_city";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TrainCity.class));
    }
}
