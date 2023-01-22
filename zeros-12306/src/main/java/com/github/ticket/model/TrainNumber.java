package com.github.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainNumber {
    private Integer id;

    private String name;

    private Integer fromStationId;

    private Integer fromCityId;

    private Integer toStationId;

    private Integer toCityId;

    private String trainType;

    private Short type;

    private Integer seatNum;

}