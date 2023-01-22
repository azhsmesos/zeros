package com.github.ticket.view;

import com.github.ticket.model.TrainStation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainStationView {

    private Integer id;

    private String name;

    private Integer cityId;

    private String cityName;
}
