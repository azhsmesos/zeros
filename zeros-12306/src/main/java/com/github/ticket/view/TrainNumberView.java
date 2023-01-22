package com.github.ticket.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainNumberView {

    private Integer id;

    private String name;

    private Integer fromStationId;

    private Integer fromCityId;

    private Integer toStationId;

    private Integer toCityId;

    private String trainType;

    private Short type;

    private Integer seatNum;

    private String fromStation;

    private String toStation;
}
