package com.github.ticket.dao.baseDAO;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.ticket.model.TrainNumber;
import com.google.common.collect.Maps;


/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Repository
public class TrainNumberDAO {

    @Resource(name = "masterDBTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<TrainNumber> getAllNumber() {
        String sql = "select * from train_number";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TrainNumber.class));
    }

    public List<TrainNumber> queryNumberInIds(List<Integer> ids) {
        System.out.println("ids: "+ ids);
        String sql = "select * from train_number where id in (?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TrainNumber.class), ids);
    }
}
