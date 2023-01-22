package com.github.ticket.dao.baseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public TrainStation selectByPrimaryKey(Integer id) {
        String sql = "select * from train_station where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<TrainStation>() {
            @Override
            public TrainStation mapRow(ResultSet rs, int rowNum) throws SQLException {
                return TrainStation.builder().build();
            }
        });
    }

    public boolean insert(TrainStation station) {
        String sql = "insert into train_station (name, city_id) values (?, ?)";
        return jdbcTemplate.update(sql, station.getName(), station.getCityId()) > 0;
    }

    public boolean update(TrainStation station) {
        String sql = "update train_station set name = ?, city_id = ? where id = ?";
        return jdbcTemplate.update(sql, station.getName(), station.getCityId(), station.getId()) > 0;
    }

    public int countByIdAndNameAndCityId(String name, Integer stationId, Integer cityId) {
        String sql = "select count(*) from train_station where name = ? and city_id = ?";
        if (stationId != null) {
            sql += " and id = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, name, cityId, stationId);
        }
        return jdbcTemplate.queryForObject(sql, Integer.class, name, cityId);
    }
}
