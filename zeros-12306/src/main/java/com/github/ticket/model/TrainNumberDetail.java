package com.github.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainNumberDetail {
    private Integer id;

    private Integer trainNumberId;

    private Integer fromStationId;

    private Integer fromCityId;

    private Integer toStationId;

    private Integer toCityId;

    private Integer stationIndex;

    private Integer relativeMinute;

    private Integer waitMinute;

    private String money;

}