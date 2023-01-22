package com.github.ticket.dao.baseDAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.ticket.model.TrainNumberDetail;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Repository
public class TrainNumberDetailDAO {

    @Resource(name = "masterDBTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<TrainNumberDetail> getAllNumberDetail() {
        String sql = "select * from train_number_detail";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TrainNumberDetail.class));
    }
}
