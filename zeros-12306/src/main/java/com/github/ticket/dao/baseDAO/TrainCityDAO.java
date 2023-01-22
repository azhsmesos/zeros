package com.github.ticket.dao.baseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.ticket.model.TrainCity;
import com.github.ticket.param.TrainCityParam;

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

    public TrainCity selectByPrimaryKey(Integer id) {
        String sql = "select * from train_city where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<TrainCity>() {
            @Override
            public TrainCity mapRow(ResultSet rs, int rowNum) throws SQLException {
                return TrainCity.builder().build();
            }
        });
    }

    public boolean insert(TrainCity city) {
        String sql = "insert into train_city (name) values (?)";
        return jdbcTemplate.update(sql, city.getName()) > 0;
    }

    public boolean update(TrainCity city) {
        String sql = "update train_city set name = ? where id = ?";
        return jdbcTemplate.update(sql, city.getName(), city.getId()) > 0;
    }

    public Integer countByNameAndId(String name, Integer cityId) {
        // todo ID可以传也可以不传 需要优化下
        String sql = "select count(*) from train_city where name = ?";
        if (cityId != null) {
            sql += " and id = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, name, cityId);
        }
        return jdbcTemplate.queryForObject(sql, Integer.class, name);
    }
}
