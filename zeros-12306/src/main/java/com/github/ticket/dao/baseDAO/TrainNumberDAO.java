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

    public TrainNumber findTrainName(String name) {
        String sql = "select * from train_number where name = ?";
        return jdbcTemplate.queryForObject(sql, TrainNumber.class, name);
    }

    public boolean insert(TrainNumber number) {
        String sql =
                "insert into train_number(name, from_station_id, from_city_id, to_station_id, to_city_id, train_type,"
                        + " type, seat_num) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, number.getName(), number.getFromStationId(), number.getFromCityId(),
                number.getToStationId(), number.getToCityId(), number.getTrainType(), number.getType(),
                number.getSeatNum()) > 0;
    }

    public boolean update(TrainNumber number) {
        String sql = "update train_number set name = ?, train_type = ?, type = ?, seat_num = ? where id = ?";
        return jdbcTemplate.update(sql, number.getName(), number.getTrainType(), number.getType(), number.getSeatNum(),
                number.getId()) > 0;
    }

}
